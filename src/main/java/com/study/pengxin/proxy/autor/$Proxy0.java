package com.study.pengxin.proxy.autor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class $Proxy0 implements com.study.pengxin.proxy.service.UserService{
InvocationHandler h;
public $Proxy0(InvocationHandler h){
this.h = h;
}
public String say(String paramString) throws Throwable{
Method md = com.study.pengxin.proxy.service.UserService.class.getMethod("say",new Class[]{java.lang.String.class });
return (String)this.h.invoke(this,md,new Object[]{paramString});
}
public String excutor() throws Throwable{
Method md = com.study.pengxin.proxy.service.UserService.class.getMethod("excutor",new Class[]{});
return (String)this.h.invoke(this,md,null);
}

}