import Controller.Controller;
import Model.commands.ExitCommand;
import Model.commands.RunExampleCommand;
import Model.expressions.ArithmeticExpression;
import Model.expressions.RationalExpression;
import Model.expressions.ReadHeapExpression;
import Model.expressions.ValueExpression;
import Model.expressions.VariableExpression;
import Model.state.PrgState;
import Model.statements.AssignStmt;
import Model.statements.CloseFIleStatement;
import Model.statements.CompStmt;
import Model.statements.HeapAllocStmt;
import Model.statements.HeapWriteStmt;
import Model.statements.IStmt;
import Model.statements.IfStmt;
import Model.statements.NoOperationStatement;
import Model.statements.OpenFileStatement;
import Model.statements.PrintStmt;
import Model.statements.ReadFileStatement;
import Model.statements.VariableDeclarationStatement;
import Model.statements.WhileStmt;
import Model.types.BoolType;
import Model.types.IntType;
import Model.values.BoolValue;
import Model.values.IntValue;
import Model.values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import java.util.List;
import java.util.Scanner;

public class App {
public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the log file: ");
        String path = scanner.nextLine();

//        String ex1 = "int v; v=2; Print(v)";
        IStmt ex1= new CompStmt(
                new VariableDeclarationStatement("v",new IntType()),
                new CompStmt(new AssignStmt("v",
                        new ValueExpression(new IntValue(2))),
                        new PrintStmt(new VariableExpression("v"))));

        PrgState prg1 = new PrgState(ex1);
        List<PrgState> prgList1 = List.of(prg1);
        IRepository repo1 = new Repository(prgList1, path);
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
        IRepository repo2 = new Repository(prgList2, path);
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
        IRepository repo3 = new Repository(prgList3, path);
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
        IRepository repo4 = new Repository(prgList4, path);
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
                                        new PrintStmt(new RationalExpression(new VariableExpression("a"), new VariableExpression("b"), 4))
                                )
                        )
                )
        );

        PrgState prg5 = new PrgState(ex5);
        List<PrgState> prgList5 = List.of(prg5);
        IRepository repo5 = new Repository(prgList5, path);
        Controller ctrl5 = new Controller(repo5);

        //basic heap allocation
        //v=10;new(v,20);new(a,22);print(v);print(a)
        IStmt ex6 = new CompStmt(
                new VariableDeclarationStatement("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(10))),
                        new CompStmt(
                                new HeapAllocStmt("v", new ValueExpression(new IntValue(20))),
                                new CompStmt(
                                        new HeapAllocStmt("a", new ValueExpression(new IntValue(22))),
                                        new CompStmt(
                                                new PrintStmt(new VariableExpression("v")),
                                                new PrintStmt(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );

        PrgState prg6 = new PrgState(ex6);
        List<PrgState> prgList6 = List.of(prg6);
        IRepository repo6 = new Repository(prgList6, path);
        Controller ctrl6 = new Controller(repo6);

        //heap allocation with write and read
        //v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
        IStmt ex7 = new CompStmt(
                new VariableDeclarationStatement("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(10))),
                        new CompStmt(
                                new HeapAllocStmt("v", new ValueExpression(new IntValue(20))),
                                new CompStmt(
                                        new HeapAllocStmt("a", new ValueExpression(new IntValue(22))),
                                        new CompStmt(
                                                new HeapWriteStmt(new VariableExpression("a"), new ValueExpression(new IntValue(30))),
                                                new CompStmt(
                                                        new PrintStmt(new VariableExpression("a")),
                                                        new CompStmt(
                                                                new PrintStmt(new ReadHeapExpression(new VariableExpression("a"))),
                                                                new AssignStmt("a", new ValueExpression(new IntValue(0)))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        PrgState prg7 = new PrgState(ex7);
        List<PrgState> prgList7 = List.of(prg7);
        IRepository repo7 = new Repository(prgList7, path);
        Controller ctrl7 = new Controller(repo7);

        // double reference
        // Ref Ref int v;new(v,20);new(a,v);print(rH(a));print(a)
        IStmt ex8 = new CompStmt(
                new VariableDeclarationStatement("v", new IntType()),
                new CompStmt(
                        new HeapAllocStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new HeapAllocStmt("a", new VariableExpression("v")),
                                new CompStmt(
                                        new PrintStmt(new ReadHeapExpression(new VariableExpression("a"))),
                                        new PrintStmt(new VariableExpression("a"))
                                        )
                                )
                        )
                );

        PrgState prg8 = new PrgState(ex8);
        List<PrgState> prgList8 = List.of(prg8);
        IRepository repo8 = new Repository(prgList8, path);
        Controller ctrl8 = new Controller(repo8);
        

        // ref int v; new(v,20); ref ref int a; new (a,v); new(v,30); print(rH(rH(a)))
        // THIS WILL NOT WORK
        IStmt ex9 = new CompStmt(
                new VariableDeclarationStatement("v", new IntType()),
                new CompStmt(
                        new HeapAllocStmt("v", new ValueExpression(new IntValue(20))),
                        new CompStmt(
                                new HeapAllocStmt("a", new VariableExpression("v")),
                                new CompStmt(
                                        new HeapAllocStmt("v", new ValueExpression(new IntValue(30))),
                                        new PrintStmt(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))
                                        )
                                )
                        )
                ));

        PrgState prg9 = new PrgState(ex9);
        List<PrgState> prgList9 = List.of(prg9);
        IRepository repo9 = new Repository(prgList9, path);
        Controller ctrl9 = new Controller(repo9);

        //while statement
        //v=4; (while (v>0) print(v);v=v-1)
        IStmt ex10 = new CompStmt(
                new VariableDeclarationStatement("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpression(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(
                                        new RationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)),3),
                                        new CompStmt(
                                                new PrintStmt(new VariableExpression("v")),
                                                new AssignStmt("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 2))
                                        )
                                ),
                                new NoOperationStatement() //this is needed because the last statement in the while is an assign statement
                                                          //so we need to add a NoOperationStatement to the end of the program
                        )
                )
        );

        PrgState prg10 = new PrgState(ex10);
        List<PrgState> prgList10 = List.of(prg10);
        IRepository repo10 = new Repository(prgList10, path);
        Controller ctrl10 = new Controller(repo10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctrl5));
        menu.addCommand(new RunExampleCommand("6", ex6.toString(), ctrl6));
        menu.addCommand(new RunExampleCommand("7", ex7.toString(), ctrl7));
        menu.addCommand(new RunExampleCommand("8", ex8.toString(), ctrl8));
        menu.addCommand(new RunExampleCommand("9", ex9.toString(), ctrl9));
        menu.addCommand(new RunExampleCommand("10", ex10.toString(), ctrl10));

        menu.show();
}
}