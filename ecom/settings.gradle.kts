rootProject.name = "ecom"

include(":base")
project(":base").projectDir = File("../base")

include(":scheduler")
project(":scheduler").projectDir = File("../scheduler")
