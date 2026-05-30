package com.panini.ticketsupport.core

object UserMessages {
    object Auth {
        const val REQUIRED_FIELDS = "Por favor ingrese su correo y contraseña"
        const val LOGIN_FAILED = "No se pudo iniciar sesión. Intente nuevamente"
    }

    object Ticket {
        const val REQUIRED_FIELDS = "El título y el proveedor son obligatorios"
        const val CREATE_SUCCESS = "Ticket creado exitosamente"
        const val STATUS_UPDATED = "Estado actualizado correctamente"
        const val PRIORITY_UPDATED = "Prioridad actualizada correctamente"
        const val NOT_FOUND = "Ticket no encontrado"
    }

    object Network {
        const val COULD_NOT_CONNECT = "No se pudo conectar al servidor"
    }

    object FeatureFlags {
        const val FEATURE_DISABLED = "Esta funcionalidad está deshabilitada"
    }
}
