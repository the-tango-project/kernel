# The Apeiron Project

The Apeiron project is inspired by [The Kukulkan Project](https://github.com/kukulkan-project), witch is looking for a lightweight code generator kernel base on plugin infraestructure.

    The apeiron is central to the cosmological theory created by Anaximander, a 6th-century BC pre-Socratic Greek philosopher whose work is mostly lost. From the few existing fragments, we learn that he believed the beginning or ultimate reality (arche) is eternal and infinite, or boundless (apeiron), subject to neither old age nor decay, which perpetually yields fresh materials from which everything we can perceive is derived.[4] Apeiron generated the opposites (hot–cold, wet–dry, etc.) which acted on the creation of the world (cf. Heraclitus). Everything is generated from apeiron and then it is destroyed by going back to apeiron, according to necessity.[5] He believed that infinite worlds are generated from apeiron and then they are destroyed there again.[6]



## Configuration

Add maven dependency into the `pom.xml` file

```xml
<dependency>
	<groupId>org.apeiron</groupId>
	<artifactId>apeiron-kernel-starter</artifactId>
	<version>0.0.7-SNAPSHOT</version>
</dependency>
```

Add Mongock configuration into `Application.yml` file

```yml
mongock:
  migration-scan-package:
    - com.example.your.component.configuration.dbmigrations
    - org.apeiron.kernel.configuration.dbmigrations

```

### Configurate the kernel loggin level

If you want to debug the kernel, you can do in the next way:

In the `.env` file
```shell
LOGGING_LEVEL_ORG_APEIRON_KERNEL=DEBUG
```

In the `application.yml` file
```yml
logging:
  level:
    org.apeiron.kernel: DEBUG
```

## Development

Libraries specifications

1. Java 21 and above
2. Maven 3.2.5 and above

TIn order to build the project we can just type the next command:

```shell
./mvnw
```

