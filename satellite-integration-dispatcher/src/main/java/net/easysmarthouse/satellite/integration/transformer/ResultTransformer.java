package net.easysmarthouse.satellite.integration.transformer;

public interface ResultTransformer<Request, Response> {

    public Response transform(Request request);

}
