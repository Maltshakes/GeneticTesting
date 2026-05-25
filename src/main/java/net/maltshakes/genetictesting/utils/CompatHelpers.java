package net.maltshakes.genetictesting.utils;

import net.minecraft.resources.ResourceLocation;

public class CompatHelpers {

    /**
     * Creates a {@link ResourceLocation} using a backwards-compatible fallback mechanism.
     *
     * <p>Attempts to dynamically invoke the modern {@code fromNamespaceAndPath} method via
     * reflection to support newer environments. Safely falls back to the traditional constructor if
     * the modern method is missing in older runtime environments.
     *
     * @param namespace The namespace string (mod ID).
     * @param path The path string resource (texture or JSON path).
     * @return A valid {@link ResourceLocation} instance matching the given namespace and path.
     */
    @SuppressWarnings("removal")
    public static ResourceLocation getCompatibleResourceLocation(String namespace, String path) {
        try {
            // Try latest version of forge method
            java.lang.reflect.Method method =
                    ResourceLocation.class.getMethod(
                            "fromNamespaceAndPath", String.class, String.class);
            return (ResourceLocation) method.invoke(null, namespace, path);
        } catch (Exception e) {
            // Fallback to deprecated version
            return new ResourceLocation(namespace, path);
        }
    }
}
