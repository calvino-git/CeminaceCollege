package com.github.adminfaces.starter.app;

import jakarta.annotation.PostConstruct;

import java.io.Serializable;

//@Singleton
//@Startup
public class InitAppMB implements Serializable {


    @PostConstruct
    public void init() {

    }
//
//    private void create(int i) {
//        crudService.insert(new Car().model("model " + i).name("name" + i).price(Double.valueOf(i)));
//    }
//
//    public static Map toMap(Context ctx) throws NamingException {
//        String namespace = ctx instanceof InitialContext ? ctx.getNameInNamespace() : "";
//        HashMap<String, Object> map = new HashMap();
//        System.out.println("> Listing namespace: " + namespace);
//        NamingEnumeration<NameClassPair> list = ctx.list(namespace);
//        while (list.hasMoreElements()) {
//            NameClassPair next = list.next();
//            String name = next.getName();
//            String jndiPath = namespace + name;
//            Object lookup;
//            try {
//                System.out.println("> Looking up name: " + jndiPath);
//                Object tmp = ctx.lookup(jndiPath);
//                if (tmp instanceof Context) {
//                    System.out.println("OK: " + tmp.getClass().getName());
//                    lookup = toMap((Context) tmp);
//                } else {
//                    System.out.println("NO : " + tmp.getClass().getName());
//                    lookup = tmp.toString();
//                }
//            } catch (Throwable t) {
//                System.err.println("ERROR : " + jndiPath + " : " + t.getLocalizedMessage());
//                lookup = t.getMessage();
//            }
//            map.put(name, lookup);
//        }
//        return map;
 //   }
//
    

}
