package com.folder.user_service.util;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public final class UUIDUtils {

      private UUIDUtils() {
          throw new UnsupportedOperationException("Utility class");
      }

    /**
     * Tao UUIDv7 (Time Ordered UUID)
     */
    public static UUID generateUUIDv7() {
        return UuidCreator.getTimeOrdered();
    }

}

