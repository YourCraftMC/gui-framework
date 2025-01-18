rootProject.name = "gui-framework"

include(":api")
project(":api").name = "gui-framework-api"

include(":core")
project(":core").name = "gui-framework-core"

include(":test-plugin")
project(":test-plugin").name = "gui-framework-test-plugin"