import java.io.File

class PasswordManager {
    private val passwords = mutableMapOf<String, String>()

    // Add function
    fun addPassword(service: String, password: String) {
        passwords[service] = password
        println("Password added for $service")
    }

    // Retrieve password function
    fun retrievePassword(service: String): String? {
        return passwords[service]?.let {
            println("Password for $service: $it")
            it
        } ?: run {
            println("No Password found for $service")
            null
        }
    }

    //Delete password function
    fun deletePassword(service: String) {
        if (passwords.remove(service) != null) {
            println("Password deleted for $service")

        } else {
            println("No Password found for $service")
        }
    }
    // Store password function
    fun storePasswords(filename: String) {
        File(filename).printWriter().use { out ->
            passwords.forEach { (service, password) ->
                out.println("$service:$password")
            }
        }
        println("Passwords stored in $filename")
    }

    // Loads passwords from a file
    fun loadPasswords(filename: String) {
        val file = File(filename)
        if (file.exists()) {
            file.forEachLine {
                val (service, password) = it.split(":")
                passwords[service] = password

            }
            println("Passwords loaded from $filename")
        } else {
            println("File $filename does not exist")
        }
    }

}

// Where all the magic happens
fun main()
{
    val manager = PasswordManager()

    while (true)
    {
        println("\nWhat would you like to do?")
        println("1. Add Password")
        println("2. Retrieve Password")
        println("3. Delete Password")
        println("4. Store Passwords to file")
        println("5. Load passwords from file")
        println("6. Exit")

        when(readLine()?.toIntOrNull())
        {
            // To add the password
            1 -> {
                print("Enter service name: ")
                val service = readLine() ?: ""
                print("Enter password: ")
                val password = readLine() ?: ""
                manager.addPassword(service, password)
            }

            // To retrieve the password
            2 -> {
                print("Enter service name: ")
                val service = readLine() ?: ""
                manager.retrievePassword(service)
            }

            // To delete the pasword
            3 -> {
                print("Enter service name: ")
                val service = readLine() ?: ""
                manager.deletePassword(service)
            }

            // Stores passwords to a file for when you get out of the program
            4 -> {
                print("Enter filename to store passwords: ")
                val filename = readLine() ?: ""
                manager.storePasswords(filename)
            }

            // Loads passwords from a chosen file
            5 -> {
                print("Enter filename to load passwords from: ")
                val filename = readLine() ?: ""
                manager.loadPasswords(filename)
            }

            // Exits the program
            6 -> {
                println("Exiting...")
                break
            }
            else -> println("Invalid option, please try again.")
        }

    }

}
