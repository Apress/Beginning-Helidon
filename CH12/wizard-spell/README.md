# Chapter 12. Integration with Coherence.

## Instructions
  
### Build the project

```bash
mvn clean package
```

### Run the Application

```bash  
mvn exec:exec
```

### Run commands

```bash
curl -X POST -H "Content-Type: application/json" -d '{"wizardName" : "Oz", "spell":"Bless you!"}' http://localhost:7001/api/spell
```

Then
```asciidoc
curl localhost:7001/api/spell 
```

The ourput should be
```asciidoc
[{"spell":"Bless you!","wizardName":"Oz"}]
```

## References

* [Coherence CE](https://coherence.community/)
* [Helidon](https://helidon.io/)



