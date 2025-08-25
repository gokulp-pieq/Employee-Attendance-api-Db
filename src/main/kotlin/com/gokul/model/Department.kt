package com.gokul.model

enum class Department(val id :Int) {
    SOFTWARE_DEVELOPMENT(1),
    QUALITY_ASSURANCE(2),
    IT_SUPPORT(3),
    NETWORK_ADMINISTRATION(4),
    CYBERSECURITY(5),
    DATABASE_ADMINISTRATION(6),
    SYSTEM_ADMINISTRATION(7),
    DEVOPS(8),
    CLOUD_COMPUTING(9),
    DATA_SCIENCE(10),
    PROJECT_MANAGEMENT(11),
    BUSINESS_ANALYSIS(12),
    UX_UI_DESIGN(13),
    TECHNICAL_WRITING(14);

    companion object {
        fun fromName(name: String): Department? =
            Department.entries.find { it.name.equals(name, ignoreCase = true) }
    }
}
