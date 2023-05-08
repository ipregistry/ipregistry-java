package co.ipregistry.datasets;

/**
 * How the dataset file should be loaded in memory.
 */
public enum LoadingMode {
    /**
     * Memory mapped. Only a small and fixed part remains in memory. Disk lookup may happen.
     */
    MEMORY_MAPPED,
    /**
     * All data is loaded in memory. No disk lookup expected.
     */
    MEMORY
}
