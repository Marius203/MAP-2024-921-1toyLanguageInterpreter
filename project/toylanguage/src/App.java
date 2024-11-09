import Controller.Controller;
import Model.commands.ExitCommand;
import Model.commands.RunExampleCommand;
import Model.expressions.ArithmeticExpression;
import Model.expressions.RationalExpression;
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
import java.util.List;

public class App {
public static void main(String[] args) {

//        String ex1 = "int v; v=2; Print(v)";
        IStmt ex1= new CompStmt(
                new VariableDeclarationStatement("v",new IntType()),
                new CompStmt(new AssignStmt("v",
                        new ValueExpression(new IntValue(2))),
                        new PrintStmt(new VariableExpression("v"))));

        PrgState prg1 = new PrgState(ex1);
        List<PrgState> prgList1 = List.of(prg1);
        IRepository repo1 = new Repository(prgList1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);
        
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
        PrgState prg2 = new PrgState(ex2);
        List<PrgState> prgList2 = List.of(prg2);
        IRepository repo2 = new Repository(prgList2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);

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

        PrgState prg3 = new PrgState(ex3);
        List<PrgState> prgList3 = List.of(prg3);
        IRepository repo3 = new Repository(prgList3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);

        //read from file two numbers and print them
        IStmt openFile = new OpenFileStatement(new ValueExpression(new StringValue("test.in")));
        IStmt readFile = new ReadFileStatement(new ValueExpression(new StringValue("test.in")), "var");
        IStmt readFile2 = new ReadFileStatement(new ValueExpression(new StringValue("test.in")), "var2");
        IStmt printVar = new PrintStmt(new VariableExpression("var"));
        IStmt printVar2 = new PrintStmt(new VariableExpression("var2"));
        IStmt closeFile = new CloseFIleStatement(new ValueExpression(new StringValue("test.in")));
        IStmt ex4 = new CompStmt(openFile, new CompStmt(readFile, new CompStmt(printVar, new CompStmt(readFile2, new CompStmt(printVar2, closeFile)))));
        
        PrgState prg4 = new PrgState(ex4);
        List<PrgState> prgList4 = List.of(prg4);
        IRepository repo4 = new Repository(prgList4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);

        // print(a<b)
        IStmt ex5 = new CompStmt(
                new VariableDeclarationStatement("a", new IntType()),
                new CompStmt(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompStmt(
                                new AssignStmt("a", new ValueExpression(new IntValue(2))),
                                new CompStmt(
                                        new AssignStmt("b", new ValueExpression(new IntValue(5))),
                                        new PrintStmt(new RationalExpression(new VariableExpression("a"), new VariableExpression("b"), 1))
                                )
                        )
                )
        );

        PrgState prg5 = new PrgState(ex5);
        List<PrgState> prgList5 = List.of(prg5);
        IRepository repo5 = new Repository(prgList5, "log5.txt");
        Controller ctrl5 = new Controller(repo5);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctrl5));

        menu.show();
}
}