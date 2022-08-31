//   keeping track of components:
// SignedInUser x
// UserInput x
// SignInCommands x
// UserCommands /
// AdminCommands /
// MenuPrintOptions x
// UserRegistry x
// UserRegistrySearch x
// UserRegistryAddUser x
// UserImporter x
// UserExporter x
// User x
// AdminRegistry x
// AdminRegistrySearch x
// AdminRegistryAddAdmin x
// AdminImporter x
// AdminExporter x
// Admin x
// BookRegistry x
// BookRegistrySearch x
// BookJsonImporter x
// BookJsonExporter x
// Book x
// BookListPrinter x
// LibraryJSONExporter o
// ReportHandler o
// ReportRequester o
// CheckingHandler o
// CheckoutRequester o
// ReturnRequester o
// IdGenerator x
// JsonToBookListCompiler x
// CsvToJsonConverter x
// EnvironmentSetup x

public class Main {
    public static void main(String[] args) {

        EnvironmentSetup environment = new EnvironmentSetup();
        environment.setup();
    }
}