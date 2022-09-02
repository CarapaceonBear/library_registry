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
// BookRegistryLoan x
// BookLoaner x
// BookJsonImporter x
// BookJsonExporter x
// Book x
// BookListPrinter x
// LibraryJSONExporter o
// ReportHandler o
// ReportRequester o
// UserBookListModifier o
// IdGenerator x
// JsonToBookListCompiler x
// CsvToJsonConverter x
// EnvironmentSetup x

// to do:
//  modify users book list when loaning
//  update library file on shutdown

public class Main {
    public static void main(String[] args) {

        EnvironmentSetup environment = new EnvironmentSetup();
        environment.setup();
    }
}