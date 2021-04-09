package org.newsio.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: newsio.proto")
public final class NewsServiceGrpc {

  private NewsServiceGrpc() {}

  public static final String SERVICE_NAME = "org.newsio.grpc.NewsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.newsio.grpc.Newsio.noParams,
      org.newsio.grpc.Newsio.News> getFetchLatestNewsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "fetchLatestNews",
      requestType = org.newsio.grpc.Newsio.noParams.class,
      responseType = org.newsio.grpc.Newsio.News.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.newsio.grpc.Newsio.noParams,
      org.newsio.grpc.Newsio.News> getFetchLatestNewsMethod() {
    io.grpc.MethodDescriptor<org.newsio.grpc.Newsio.noParams, org.newsio.grpc.Newsio.News> getFetchLatestNewsMethod;
    if ((getFetchLatestNewsMethod = NewsServiceGrpc.getFetchLatestNewsMethod) == null) {
      synchronized (NewsServiceGrpc.class) {
        if ((getFetchLatestNewsMethod = NewsServiceGrpc.getFetchLatestNewsMethod) == null) {
          NewsServiceGrpc.getFetchLatestNewsMethod = getFetchLatestNewsMethod = 
              io.grpc.MethodDescriptor.<org.newsio.grpc.Newsio.noParams, org.newsio.grpc.Newsio.News>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "org.newsio.grpc.NewsService", "fetchLatestNews"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.newsio.grpc.Newsio.noParams.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.newsio.grpc.Newsio.News.getDefaultInstance()))
                  .setSchemaDescriptor(new NewsServiceMethodDescriptorSupplier("fetchLatestNews"))
                  .build();
          }
        }
     }
     return getFetchLatestNewsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NewsServiceStub newStub(io.grpc.Channel channel) {
    return new NewsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NewsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NewsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NewsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NewsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NewsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void fetchLatestNews(org.newsio.grpc.Newsio.noParams request,
        io.grpc.stub.StreamObserver<org.newsio.grpc.Newsio.News> responseObserver) {
      asyncUnimplementedUnaryCall(getFetchLatestNewsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFetchLatestNewsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                org.newsio.grpc.Newsio.noParams,
                org.newsio.grpc.Newsio.News>(
                  this, METHODID_FETCH_LATEST_NEWS)))
          .build();
    }
  }

  /**
   */
  public static final class NewsServiceStub extends io.grpc.stub.AbstractStub<NewsServiceStub> {
    private NewsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceStub(channel, callOptions);
    }

    /**
     */
    public void fetchLatestNews(org.newsio.grpc.Newsio.noParams request,
        io.grpc.stub.StreamObserver<org.newsio.grpc.Newsio.News> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getFetchLatestNewsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NewsServiceBlockingStub extends io.grpc.stub.AbstractStub<NewsServiceBlockingStub> {
    private NewsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<org.newsio.grpc.Newsio.News> fetchLatestNews(
        org.newsio.grpc.Newsio.noParams request) {
      return blockingServerStreamingCall(
          getChannel(), getFetchLatestNewsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NewsServiceFutureStub extends io.grpc.stub.AbstractStub<NewsServiceFutureStub> {
    private NewsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NewsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NewsServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_FETCH_LATEST_NEWS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NewsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NewsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FETCH_LATEST_NEWS:
          serviceImpl.fetchLatestNews((org.newsio.grpc.Newsio.noParams) request,
              (io.grpc.stub.StreamObserver<org.newsio.grpc.Newsio.News>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NewsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NewsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.newsio.grpc.Newsio.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NewsService");
    }
  }

  private static final class NewsServiceFileDescriptorSupplier
      extends NewsServiceBaseDescriptorSupplier {
    NewsServiceFileDescriptorSupplier() {}
  }

  private static final class NewsServiceMethodDescriptorSupplier
      extends NewsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NewsServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NewsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NewsServiceFileDescriptorSupplier())
              .addMethod(getFetchLatestNewsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
