# superops.ai

Movie Ticketing System
Following are the components :
1. registry service - configuring eureka server
2. micro_service - configuring having all the properties of other instances
3. apigateway - act as load balancer for the request and act as zuul proxy
4. userservice - service that renders the movie ticketing logic

(Currently I have added routing of zuul proxy to userservice. Due to some issue occuring, it's not able to route. I suggest you to use http://localhost:userserviceport_number/api/v1/user/{paths}

Implementations:
1. Can add user or remove user from the system.
2. Can add theatre or cinema hall(which statically adds the individual screening as well)
3. Can add movie 
4. Can book the seats based on theatre, movie , user login and other params like seats requested, no of seats etc
5. While booking, it will always check for the same seats request and see the possibility of max no of seats or any tie between users
6. After booking, it goes for payment. Currently it checks for random payment status and checks whether the payment is done within 2 mins.
