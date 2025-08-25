package com.gokul.model


enum class Role(val id: Int) {
    DEVELOPER(1), DESIGNER(2), INTERN(3), MANAGER(4);

    companion object {
        fun fromName(name: String): Role? =
            entries.find { it.name.equals(name, ignoreCase = true) }
    }
}
