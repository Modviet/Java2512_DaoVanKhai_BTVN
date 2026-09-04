package com.folder.productservice.util;

import java.text.Normalizer;

public final class SlugUtil {

      private SlugUtil() {

      }

      public static String toSlug(String value) {

          if(value == null || value.isBlank()) {
              return "";
          }

          String slug = Normalizer.normalize(value, Normalizer.Form.NFD)
                  .replaceAll("\\p{M}","")
                  .replaceAll("[^a-zA-Z0-9\\s-]","")
                  .trim()
                  .replaceAll("\\s+","-")
                  .toLowerCase();

          return slug;
      }
}
