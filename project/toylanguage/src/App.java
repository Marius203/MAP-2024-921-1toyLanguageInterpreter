import Controller.Controller;
import Model.expressions.ArithmeticExpression;
import Model.expressions.ValueExpression;
import Model.expressions.VariableExpression;
import Model.state.PrgState;
import Model.statements.AssignStmt;
import Model.statements.CompStmt;
import Model.statements.IStmt;
import Model.statements.IfStmt;
import Model.statements.PrintStmt;
import Model.statements.VariableDeclarationStatement;
import Model.types.BoolType;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IntValue;
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

        System.out.println("Choose an example to run: ");
        Scanner Scanner = new Scanner(System.in);
        int choice = Scanner.nextInt();

        switch (choice) {
            case 1:
                PrgState prg1 = new PrgState(ex1);
                List<PrgState> prgList1 = List.of(prg1);
                IRepository repo1 = new Repository(prgList1);
                Controller ctrl1 = new Controller(repo1);
                MainView view1 = new MainView(ctrl1);
                view1.run();
                break;
            case 2:
                PrgState prg2 = new PrgState(ex2);
                List<PrgState> prgList2 = List.of(prg2);
                IRepository repo2 = new Repository(prgList2);
                Controller ctrl2 = new Controller(repo2);
                MainView view2 = new MainView(ctrl2);
                view2.run();
                break;
            case 3:
                PrgState prg3 = new PrgState(ex3);
                List<PrgState> prgList3 = List.of(prg3);
                IRepository repo3 = new Repository(prgList3);
                Controller ctrl3 = new Controller(repo3);
                MainView view3 = new MainView(ctrl3);
                view3.run();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }
}