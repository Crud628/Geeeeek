1.XML

```xml
<bean id="xiaoming" class="springBean.Student">
    <Property name="sid" value="160" />
    ...
</bean>    
```



2.@AutoWired / @Resources

```java
@AutoWired // Resources
private StudentDao studentDao;
```



3.ymal  / application

```ymal
xiaoming:
  id: 160
  name: fk
```



```application
xiaoming.id=160
xiaoming.name=fk
```

