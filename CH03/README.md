# Chapter 3 - Configuration Sample

Build and Run sample
```shell
mvn package && java -jar ./target/ch03-config.jar
```

Query JAX-RS endpoints demonstrating configuration features
```shell
curl http://localhost:8080/battle/sorcerer

{
  "name": "Merlin",
  "title": "Merlin_30",
  "level": 30,
  "orcSlayingPotions": 0,
  "invisibilityCloak": true,
  "weaponsList": "[axe, sword, wand]",
  "weaponsArray": "[axe, sword, wand]",
  "weaponsSet": "[axe, sword, wand]"
}
```

```shell
curl http://localhost:8080/battle/orcs

{
  "leader": {
    "name": "Corgoth Scullcrack",
    "level": 32
  },
  "healer": {
    "name": "Skurf Bonesharp",
    "level": 21
  }
}
```

Run sample with TEST profile
```shell
java -Dmp.config.profile=TEST -jar ./target/ch03-config.jar
```
And notice different configured levels
```shell
curl http://localhost:8080/battle/sorcerer

{
  "name": "Merlin",
  "title": "Merlin_3000",
  "level": 3000,
  "orcSlayingPotions": 0,
  "invisibilityCloak": true,
  "weaponsList": "[axe, sword, wand]",
  "weaponsArray": "[axe, sword, wand]",
  "weaponsSet": "[axe, sword, wand]"
}

```