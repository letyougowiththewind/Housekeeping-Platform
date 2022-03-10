package com.gnz.pms.aspect;

import com.alibaba.fastjson.JSONObject;
import com.gnz.pms.annotation.Log;
import com.gnz.pms.entities.SystemLog;
import com.gnz.pms.entities.SysUser;
import com.gnz.pms.service.ISystemLogService;
import com.gnz.pms.utils.IpUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@Aspect
@Component
public class LogAspect {


    @Resource
    private ISystemLogService logService;
    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.gnz.pms.annotation.Log)")
    public void operationLog(){}


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            //下面这一行我不知道取到了什么
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志，调用下面的方法
                addOperationLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time){
        //获得登录用户信息
        SysUser systemUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        SystemLog operationLog = new SystemLog();
        //获取内网地址IpUtils.intranetIp()
        //获取外网地址IpUtils.internetIp()
        operationLog.setIpAddress(IpUtils.intranetIp());
        operationLog.setRunTime(time);
        operationLog.setReturnValue(JSONObject.toJSONString(res));
//        operationLog.setId(UUID.randomUUID().toString());
        //请求参数
        Object[] args = joinPoint.getArgs();
        List<Object> argsList=new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            // 如果参数类型是请求和响应的http，则不需要拼接【这两个参数，使用JSON.toJSONString()转换会抛异常】
            if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse)
            {
                continue;
            }
            argsList.add(args[i]);
            System.out.println("==============="+argsList.toString());
            operationLog.setArgs(JSONObject.toJSONString(argsList));
        }
        operationLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId(systemUser.getUid()+"");
        operationLog.setUserName(systemUser.getNickName());
        Log annotation = signature.getMethod().getAnnotation(Log.class);
        if(annotation != null){
            operationLog.setLogLevel(annotation.level());
            Object[] args2 = joinPoint.getArgs();
            List<Object> argsList2=new ArrayList<>();
            for (int i = 0; i < args2.length; i++) {
                // 如果参数类型是请求和响应的http，则不需要拼接【这两个参数，使用JSON.toJSONString()转换会抛异常】
                if (args2[i] instanceof HttpServletRequest || args2[i] instanceof HttpServletResponse)
                {
                    continue;
                }
                argsList2.add(args2[i]);
                //在下面这行抛出的异常
                operationLog.setLogDescribe(getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),argsList2,annotation));
            }
            operationLog.setOperationType(annotation.operationType().getValue());
            operationLog.setOperationUnit(annotation.operationUnit().getValue());
        }
        //TODO 这里保存日志
        System.out.println("记录日志:" + operationLog.toString());
        int i = logService.addLog(operationLog);
        System.out.println(i);
    }

    /**
     * 对当前登录用户和占位符处理
     * @param argNames 方法参数名称数组
     * @param args 方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, List<Object> args, Log annotation){
        //获得登录用户信息
        SysUser systemUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Map<Object, Object> map = new HashMap<>(4);
        for(int i = 0;i < args.size();i++){
            map.put(argNames[i], args.get(i));
        }

        String detail = annotation.detail();
        try {
            detail = "'" + systemUser.getNickName() + "'==>" + annotation.detail();
            //下面是一种遍历map集合的方法，通过Map.entrySet遍历key和value
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();

                detail = detail.replace("{{" + k + "}}", JSONObject.toJSONString(v));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }

    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }
}