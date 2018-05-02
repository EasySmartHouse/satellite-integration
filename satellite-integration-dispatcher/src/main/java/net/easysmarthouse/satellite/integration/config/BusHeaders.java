package net.easysmarthouse.satellite.integration.config;

public enum BusHeaders {

    SEQ_ID("sequenceId"),
    AGGREGATION_SYNC_ID("aggregationSyncId");

    private final String name;

    BusHeaders(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
