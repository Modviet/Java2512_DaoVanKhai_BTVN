package com.folder.productservice.util;


public final class ProductCodeGenerator {

      private ProductCodeGenerator() {

      }

      public static String generate(String prefix, long squence) {
          return "%s-%06d".formatted(prefix, squence);
      }
}
