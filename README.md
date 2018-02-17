# SpringCloudArquitectureEnv
Spring Cloud and Netflix OSS PoC to test architecture and shenanigans.

# General gist
The app is just a basic user registration in order to login (with Oauth2) so we can access every endpoint of the app. All of this is for testing purposes of the Spring Cloud and Netflix technologies. All here is for learning purposes only.

# Content
1 - Spring Cloud and Netflix. 2 - Eureka (microservice register). 3 - Zuul (proxy gateway). 4 - Hystrix (circuit breaker). 5 - Ribbon (client tier load balancer). 6 - Spring security Oauth2. 7 - Spring Data REST and JPA. 8 - Hystrix Dashboard.

# Details
  # Eureka:
Is being used so we can register every single instances of our microservices, which in the end let us keep away from services shortages. We can have many instances of this services registration, but everyone needs to be aware of the others (some nice info: https://github.com/Netflix/eureka/wiki/Understanding-Eureka-Peer-to-Peer-Communication). In order to achieve this, we need to do as this SO question and answer: http://stackoverflow.com/questions/30850232/how-to-config-multiple-eureka-servers-from-client-in-spring-cloud.

  # Zuul:
Zuul is our gateway proxy. Its work is to redirect every single request directed to him, to the actual microservice (using Eureka so we won't hardcode any url, but just use the service registration name). Zuul by itself will decide which instance of the requested microservice should be used for each request, so if we've 3 instances of an account service, he'll decide which one to use (as long as they're available in the registration server). Zuul also has load balancing and circuit breaker capabilities, of course, not by itself but using hystrix and ribbon (if they're in the classpath); ribbon is used by default with no prior configuration but the circuit breaker needs to be anottated (using the corresponding annotation) at Zuul's spring boot application level.

Zuul also fills some headers automatically (the X-forwarded-... defined here: http://docs.aws.amazon.com/elasticloadbalancing/latest/classic/x-forwarded-headers.html#x-forwarded-for). This is so every Spring Data Rest hypermedia response redirect to the zuul gateway instead of redirecting to one of the multiple instances of the microservice we're using, which'd kill the load balancing and the gateway purpose (see: http://stackoverflow.com/questions/42428045/hypermedia-response-in-a-microservice-architecture)

  # Ribbon:
The load balancer. Could be used by annotation (using the RestTemplate like in the RestcloudApplication project of myself) or just leaving Zuul to handle it for you.

  # Hystrix:
Its job is to break the requested process if there's somekind of shortcage (as a circuit breaker in an actual circuit). Let's say our DB is not working but we've like 2000 request to use a certain endpoint which uses the DB, well, then hystrix'll break that in order to not saturate the system, following the principle of fail fast.

  #  Hystrix Dashboard:
The dashboard is fed up by every service that has circuit breaking enabled (aka annotated with @EnableHystrix or @EnableCircuitBreaker). When it's enabled a circuit breaker, it automatically feed its root endpoint + /hystrix.stream and this are the metrics the dashboard use to their graphs. Also, when using zuul, there are no thread pools as it uses semaphore isolation (https://github.com/spring-cloud/spring-cloud-netflix/issues/770). You can monitor every service directly if they're annotated or also you can just monitor zuul to watch every service managed by it. Turbine requires RabbitMQ server installation so I'm gonna leave it alone for now. Nice info regarding async: https://github.com/Netflix/Hystrix/issues/1427#issuecomment-263313877.

  # Oauth2
It uses Spring security but also adds a new layer over the already known UserDetails, which is the ClientDetails. Basically, every microservice (or even the frontend webapp) could be a client. This let us define service access to a client level, so the abstraction level goes up as well as the control we now have for users accessing a client or many clients. Bro tips: ../oauth2/token is the token endpoint. This endpoint should be accesed with the application/x-www-form-urlencoded. We can send the username, the password, the clientid (which says what client you're trying to access), the grant_type (which says what type of authing you want to use. This case will initially cover the user), etc. This guides explains a bit more and could also help: https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v. If you're getting an obnoxious XML response (maybe when accessing a certain endpoint while unauthorized or 403 response) then add the header Accept: application/json. Nice docs: http://stytex.de/blog/2016/02/01/spring-cloud-security-with-oauth2/. Official docs with nice general info: http://projects.spring.io/spring-security-oauth/docs/oauth2.html. Excellent SO answer to get to work the JdbcTokenStore: https://stackoverflow.com/questions/36904178/how-to-persist-oauth-access-tokens-in-spring-security-jdbc.

To use a authorization code grant, you must first go to http://localhost:4444/login (it'll redirect to http://localhost:9999/auth/login), then we must type down the username and pass (this ones must be previously registered into or Oauth2 Server microservice), we will be redirected to somethin like this http://localhost:9999/auth/oauth/authorize?client_id=gateway&redirect_uri=http://localhost:4444/login&response_type=code&state=9937Fr, in that page, we must click the "approve" button and then we should end in an error site (this is just because I don't have any page to show there and also the path is blocked) and, within the uri params, it should be the code. Next, we copy it and then use it to login (pic is in request samples). It should return an access token and from then on everything is the same. The last part (sending the code to the server) can be done via gateway (there's also a pic for this one).

The redirect Uri must be the same in every step of the authorization code login. So, if our app entry point is http://localhost:4444/login, the registered Uri of the that client should be the exact same. If not, it shows a misleading invalid code error. A little info regarding changing this behavior: https://github.com/spring-cloud/spring-cloud-security/issues/86/https://stackoverflow.com/questions/33812471/spring-oauth-redirect-uri-not-using-https

# Request samples
There're pics of the rest client request in order to test the app.

The /oauth/token endpoint is protected. In order to POST to it, you need to send the client_id and the client_secret. There're two ways to do it: one is sending it into the url like "clientId:clientSecret@" (discouraged by specification) and the other one is sending an Authorization header with the client_id and the client_secret encoded with Base64 (aka Basic Auth); in this case we need to encode "client_id:client_secret" with base64 and set the header "Authorization: Basic [encodedString]". SO answer to this: https://stackoverflow.com/questions/47662358/client-id-and-client-secret-sent-via-param-vs-in-url/47664217#47664217
