rootProject.name = "gui-framework"

include(":api")
project(":api").name = "gui-framework-api"

include(":core")
project(":core").name = "gui-framework-core"

include(":packet:bukkit")
project(":packet:bukkit").name = "gui-framework-packet-bukkit"

include(":legacy:bukkit")
project(":legacy:bukkit").name = "gui-framework-legacy-bukkit"

include(":test-plugin")
project(":test-plugin").name = "gui-framework-test-plugin"