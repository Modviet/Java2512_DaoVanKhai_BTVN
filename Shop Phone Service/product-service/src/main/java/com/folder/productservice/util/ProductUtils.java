package com.folder.productservice.util;

public final class ProductUtils {

      private ProductUtils() {

      }

      public static boolean available(Integer stock) {

          return stock != null && stock > 0;
      }
}
