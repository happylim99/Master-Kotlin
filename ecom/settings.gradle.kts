rootProject.name = "ecom"

include(":base")
project(":base").projectDir = File("../base")

include(":scheduler")
project(":scheduler").projectDir = File("../scheduler")

include(":jpa")
project(":jpa").projectDir = File("../jpa")

//include(":jpa")
//project(":jpa").projectDir = File("../jpa/build/libs/jpa-0.0.1-SNAPSHOT.jar")
