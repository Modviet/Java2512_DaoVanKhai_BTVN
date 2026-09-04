package com.folder.order_service.util;

import java.util.UUID;

public class UuidV7Generator {

       private UuidV7Generator(){

       }

       public static UUID generate(){
           return UUID.randomUUID();
       }
}

