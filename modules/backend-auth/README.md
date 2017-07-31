# This is the module for authentication and authorization

How to use:

 * Check Authentication
 * Check Authorization
 * Check NoFoundException
 * Commond classes in module

## Initialization

Add below scriptlet in build.gradle of module that will be used

```
compileOnly project(":modules:backend-auth")
```

## Check Authentication

At **LocalServiceImpl.java file

```
BackendAuthImpl authImpl = new BackendAuthImpl();
		
boolean isAuth = authImpl.isAuth(context, security, password);
		 
if (!isAuth) {
	throw new UnauthenticationException();
}
```

## Check Authorization
```
boolean hasPermission = authImpl.hasResource(context, ModelNameKeys.SAMPLE_MODULE, ActionKeys.SAMPLE_ACTION);
		
if (!hasPermission) {
	throw new UnauthorizationException();
}
```

## Check NoFoundException
```
if (Validator.isNull(result)) {
	throw new NotFoundException();
}
```

## Common classes in module


**`package: backend.auth.api`**

`BackendAuth.java` interface of module

`BackendAuthImpl.java` implement class

`BackendUtils.java` include util methods for module


**`package: backend.auth.api.exception`**

`NotFoundException.java` exception when results are empty
 
`UnauthenticationException.java`  exception when not authentication
 
`UnauthorizationException.java` exception when not authorization



**`package: backend.auth.api.keys `**

`ActionKeys.java` define all action key of project

`ModelNameKeys.java` define all model name of project