package com.enjoy.cap10.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 日志切面类  需要动态感知到 div()方法运行时，
 * 通知方法：
 * 前置通知（@Before）：logStart()，在执行DIV()除法之前运行
 * 后置通知（@After）:logEnd(), 在方法div运行结束时  不管有没有异常  都会执行
 * 返回通知（@AfterReturning）: logReturn(), 在方法div正常结束时执行
 * 异常通知（@AfterThrowing）：logException()  在方法div出现异常后运行
 * 环绕通知（@Around）:动态代理  需要手动执行joinPoint.proceed()  其实就是执行div()  执行之前相当于前置通知  执行之后相当于后置通知
 */
@Aspect
public class LogAspects {

    /**
     * execution可以写到包前 也可以写到类前  支持模糊匹配
     */
//    @Pointcut("execution(public int com.enjoy.cap10.aop..*(..))")
    @Pointcut("execution(public int com.enjoy.cap10.aop.Calculator.*(..))")
    public void pointCut() {

    }

    /**
     * before代表在目标方法前切入  并指定在哪个方法前切入
     */
//    @Before("execution(public int com.enjoy.cap.aop.Calculator.div(int,int)")
//    @Before("execution(public int com.enjoy.cap.aop.Calculator.*(..)")
//    @Before("execution(public int com.enjoy.cap.aop.*.(..)")
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        //JoinPoint 可以获取方法运行时的各种参数 以下是获取方法名与 方法参数
        System.out.println(joinPoint.getSignature().getName() + "除法运行前...参数列表{" + Arrays.asList(joinPoint.getArgs()) + "}");

    }

    //    @After("execution(public int com.enjoy.cap.aop.Calculator.div(int,int)")
    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束...");
    }

    /**
     * 在after之后执行
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法正常返回... 运行结果是:{" + result + "}");

    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法运行异常为:{" + exception + "}");
    }

    /**
     * 有了环绕就不再执行before  和 after标注的放法了   因为before与after标注的方法都会在around里面执行
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("@Around:执行目标方法之前");   //前置通知 比before还早
        Object object = proceedingJoinPoint.proceed();
        System.out.println("@Around:执行目标方法之后");  //后置通知   比after早
        return object;

    }


}
