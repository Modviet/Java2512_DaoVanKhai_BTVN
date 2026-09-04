package com.folder.productservice.util;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public final class UuidV7Util {

      private UuidV7Util(){

      }

      public static UUID generate() {
          return UuidCreator.getTimeOrdered();
      }
}
