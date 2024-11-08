import Controller.Controller;
import Model.expressions.ArithmeticExpression;
import Model.expressions.ValueExpression;
import Model.expressions.VariableExpression;
import Model.state.PrgState;
import Model.statements.AssignStmt;
import Model.statements.CloseFIleStatement;
import Model.statements.CompStmt;
import Model.statements.IStmt;
import Model.statements.IfStmt;
import Model.statements.OpenFileStatement;
import Model.statements.PrintStmt;
import Model.statements.ReadFileStatement;
import Model.statements.VariableDeclarationStatement;
import Model.types.BoolType;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IntValue;
import Model.values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import View.MainView;
import java.util.List;
import java.util.Scanner;

public class App {
public static void main(String[] args) {
//        String ex1 = "int v; v=2; Print(v)";
        IStmt ex1= new CompStmt(
                new VariableDeclarationStatement("v",new IntType()),
                new CompStmt(new AssignStmt("v",
                        new ValueExpression(new IntValue(2))),
                        new PrintStmt(new VariableExpression("v"))));
//        String ex2 = "int a; int b; a=2+3*5; b=a+1; Print(b)";
        IStmt ex2 = new CompStmt(
                new VariableDeclarationStatement("a", new IntType()),
                new CompStmt(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), 3), 1)),
                                new CompStmt(
                                        new AssignStmt("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), 1)),
                                        new PrintStmt(new VariableExpression("b"))
                                )
                        )
                )
        );
//        String ex3 = "bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)";
        IStmt ex3 = new CompStmt(
                new VariableDeclarationStatement("a", new BoolType()),
                new CompStmt(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExpression(new BoolValue(true))),
                                new CompStmt(
                                        new IfStmt(
                                                new VariableExpression("a"),
                                                new AssignStmt("v", new ValueExpression(new IntValue(2))),
                                                new AssignStmt("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStmt(new VariableExpression("v"))
                                )
                        )
                )
        );
        while(true) {
                System.out.println("Choose an example to run: ");
                Scanner Scanner = new Scanner(System.in);
                int choice = Scanner.nextInt();

                switch (choice) {
                case 1:
                        PrgState prg1 = new PrgState(ex1);
                        List<PrgState> prgList1 = List.of(prg1);
                        //input for the file path
                        Scanner S1 = new Scanner(System.in);
                        System.out.println("Enter the file path: ");
                        IRepository repo1 = new Repository(prgList1, S1.nextLine());
                        Controller ctrl1 = new Controller(repo1);
                        MainView view1 = new MainView(ctrl1);
                        view1.run();
                        break;
                case 2:
                        PrgState prg2 = new PrgState(ex2);
                        List<PrgState> prgList2 = List.of(prg2);
                        Scanner S2 = new Scanner(System.in);
                        System.out.println("Enter the file path: ");
                        IRepository repo2 = new Repository(prgList2,S2.nextLine());
                        Controller ctrl2 = new Controller(repo2);
                        MainView view2 = new MainView(ctrl2);
                        view2.run();
                        break;
                case 3:
                        PrgState prg3 = new PrgState(ex3);
                        List<PrgState> prgList3 = List.of(prg3);
                        Scanner S3 = new Scanner(System.in);
                        System.out.println("Enter the file path: ");
                        IRepository repo3 = new Repository(prgList3, S3.nextLine());
                        Controller ctrl3 = new Controller(repo3);
                        MainView view3 = new MainView(ctrl3);
                        view3.run();
                        break;
                case 4:
                        IStmt openFile = new OpenFileStatement(new ValueExpression(new StringValue("test.in")));
                        IStmt readFile = new ReadFileStatement(new ValueExpression(new StringValue("test.in")), "var");
                        IStmt readFile2 = new ReadFileStatement(new ValueExpression(new StringValue("test.in")), "var2");
                        IStmt printVar = new PrintStmt(new VariableExpression("var"));
                        IStmt printVar2 = new PrintStmt(new VariableExpression("var2"));
                        IStmt closeFile = new CloseFIleStatement(new ValueExpression(new StringValue("test.in")));
                        IStmt ex4 = new CompStmt(openFile, new CompStmt(readFile, new CompStmt(printVar, new CompStmt(readFile2, new CompStmt(printVar2, closeFile)))));
                        PrgState prg4 = new PrgState(ex4);
                        List<PrgState> prgList4 = List.of(prg4);
                        Scanner S4 = new Scanner(System.in);
                        System.out.println("Enter the file path: ");
                        IRepository repo4 = new Repository(prgList4, S4.nextLine());
                        Controller ctrl4 = new Controller(repo4);
                        MainView view4 = new MainView(ctrl4);
                        view4.run();
                default:
                        System.out.println("Invalid choice");
                }
        }
}
}