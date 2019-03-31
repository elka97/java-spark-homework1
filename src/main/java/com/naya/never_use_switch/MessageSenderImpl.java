package com.naya.never_use_switch;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */

public class MessageSenderImpl implements MessageSender {

    private Map<Integer, Distributor> map = new HashMap<>();

    @SneakyThrows
    public MessageSenderImpl() {

    //    Reflections scanner = new Reflections("com.naya.never_use_switch");
    //    Set<Class<? extends Distributor>> classes = scanner.getSubTypesOf(Distributor.class);

        ApplicationContext context = new AnnotationConfigApplicationContext("com.naya.never_use_switch");

       //var wh=    context.getBean(WhasappDistributor.class);
       // System.out.println(wh.getMyCode());
        Map<String, Distributor> beansOfType = context.getBeansOfType(Distributor.class);


        System.out.println(beansOfType.size());

        for (Map.Entry<String, Distributor> entry : beansOfType.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());

            int code = entry.getValue().getMyCode();

           if(map.containsKey(code)) {
               throw new IllegalStateException(code+ " already in use");
           }

           map.put(code, entry.getValue());
        }

       /* for (Class<? extends Distributor> aClass : classes) {
            if (!Modifier.isAbstract(aClass.getModifiers())) {
                Distributor distributor = aClass.newInstance();
                int code = distributor.getMyCode();
                if (map.containsKey(code)) {
                    throw new IllegalStateException(code + " already in use");
                }
                map.put(code, distributor);
            }
        }*/

    }

    @Override
    public void send(Message message) {
        int type = message.getDistributionType();
        Distributor distributor = map.get(type);
        if (distributor == null) {
            throw new IllegalStateException(type + " is not supported yet");
        }
        distributor.distribute(message);

    }
}
