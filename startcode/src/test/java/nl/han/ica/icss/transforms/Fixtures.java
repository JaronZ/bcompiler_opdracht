package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class Fixtures {
    public static AST checkedLevel0() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        p {
            background-color: #ffffff;
            width: 500px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("500px"))));
        /*
        a {
            color: #ff0000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#ff0000"))));
        /*
        #menu {
            width: 520px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("520px"))));
        /*
        .menu {
            color: #000000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000"))));

        return new AST(stylesheet);
    }

    public static AST transformedLevel0() {
        return checkedLevel0();
    }

    public static AST checkedLevel1() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        LinkColor := #ff0000;
        ParWidth := 500px;
        AdjustColor := TRUE;
        UseLinkColor := FALSE;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("LinkColor"))
                        .addChild(new ColorLiteral("#ff0000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new BoolLiteral("TRUE")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("UseLinkColor"))
                        .addChild(new BoolLiteral("FALSE")));
        /*
        p {
            background-color: #ffffff;
            width: ParWidth;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("ParWidth"))));
        /*
        a {
            color: LinkColor;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("LinkColor"))));
        /*
        #menu {
            width: 520px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("520px"))));
        /*
        .menu {
            color: #000000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000"))));

        return new AST(stylesheet);
    }

    public static AST transformedLevel1() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        LinkColor := #ff0000;
        ParWidth := 500px;
        AdjustColor := TRUE;
        UseLinkColor := FALSE;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("LinkColor"))
                        .addChild(new ColorLiteral("#ff0000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new BoolLiteral("TRUE")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("UseLinkColor"))
                        .addChild(new BoolLiteral("FALSE")));
        /*
        p {
            background-color: #ffffff;
            width: 500px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("500px"))));
        /*
        a {
            color: #ff0000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#ff0000"))));
        /*
        #menu {
            width: 520px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("520px"))));
        /*
        .menu {
            color: #000000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000"))));

        return new AST(stylesheet);
    }

    public static AST checkedLevel2() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        LinkColor := #ff0000;
        ParWidth := 500px;
        AdjustColor := TRUE;
        UseLinkColor := FALSE;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("LinkColor"))
                        .addChild(new ColorLiteral("#ff0000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new BoolLiteral("TRUE")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("UseLinkColor"))
                        .addChild(new BoolLiteral("FALSE")));
        /*
        p {
            background-color: #ffffff;
            width: ParWidth;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("ParWidth"))));
        /*
        a {
            color: LinkColor;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("LinkColor"))));
        /*
        #menu {
            width: ParWidth + 2 * 10px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new AddOperation()
                                .addChild(new VariableReference("ParWidth"))
                                .addChild(new MultiplyOperation()
                                        .addChild(new ScalarLiteral(2))
                                        .addChild(new PixelLiteral("10px"))))));
        /*
        .menu {
            color: #000000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000"))));

        return new AST(stylesheet);
    }

    public static AST transformedLevel2() {
        return transformedLevel1();
    }

    public static AST checkedLevel3() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        LinkColor := #ff0000;
        ParWidth := 500px;
        AdjustColor := TRUE;
        UseLinkColor := FALSE;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("LinkColor"))
                        .addChild(new ColorLiteral("#ff0000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new BoolLiteral("TRUE")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("UseLinkColor"))
                        .addChild(new BoolLiteral("FALSE")));
        /*
        p {
            background-color: #ffffff;
            width: ParWidth;
            if[AdjustColor] {
                color: #124532;
                if[UseLinkColor]{
                    background-color: LinkColor;
                } else {
                    background-color: #000000;
                }
            }
            height: 20px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("ParWidth")))
                .addChild(new IfClause()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new Declaration("color")
                                .addChild(new ColorLiteral("#124532")))
                        .addChild(new IfClause()
                                .addChild(new VariableReference("UseLinkColor"))
                                .addChild(new Declaration("background-color")
                                        .addChild(new VariableReference("LinkColor")))
                                .addChild(new ElseClause()
                                        .addChild(new Declaration("background-color")
                                                .addChild(new ColorLiteral("#000000"))))))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("20px"))));
        /*
        a {
            color: LinkColor;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("LinkColor"))));
        /*
        #menu {
            width: ParWidth + 20px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new AddOperation()
                                .addChild(new VariableReference("ParWidth"))
                                .addChild(new PixelLiteral("20px")))));
        /*
        .menu {
            color: #000000;
            background-color: LinkColor;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new Declaration("background-color")
                        .addChild(new VariableReference("LinkColor"))));

        return new AST(stylesheet);
    }

    public static AST transformedLevel3() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        LinkColor := #ff0000;
        ParWidth := 500px;
        AdjustColor := TRUE;
        UseLinkColor := FALSE;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("LinkColor"))
                        .addChild(new ColorLiteral("#ff0000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild(new BoolLiteral("TRUE")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("UseLinkColor"))
                        .addChild(new BoolLiteral("FALSE")));
        /*
        p {
            background-color: #ffffff;
            width: 500px;
            color: #124532;
            background-color: #000000;
            height: 20px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("500px")))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#124532")))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("20px"))));
        /*
        a {
            color: #ff0000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("a"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#ff0000"))));
        /*
        #menu {
            width: 520px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new IdSelector("#menu"))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("520px"))));
        /*
        .menu {
            color: #000000;
            background-color: #ff0000;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new ClassSelector(".menu"))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#ff0000"))));

        return new AST(stylesheet);
    }

    public static AST checkedScopes() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        ParWidth := 28%;
        ParHeight := 13px + 5px;
        SpanWidth := 3 * 10% - 5%;
        SpanColor := #000000;
        DivSize := 5px + 3px * 4;
        DivColor := #abcdef;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PercentageLiteral("28%"))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParHeight"))
                        .addChild(new AddOperation()
                                .addChild(new PixelLiteral("13px"))
                                .addChild(new PixelLiteral("5px"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("SpanWidth"))
                        .addChild(new SubtractOperation()
                                .addChild(new MultiplyOperation()
                                        .addChild(new ScalarLiteral(3))
                                        .addChild(new PercentageLiteral("10%")))
                                .addChild(new PercentageLiteral("5%"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("SpanColor"))
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivSize"))
                        .addChild(new AddOperation()
                                .addChild(new PixelLiteral("5px"))
                                .addChild(new MultiplyOperation()
                                        .addChild(new PixelLiteral("3px"))
                                        .addChild(new ScalarLiteral(4)))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivColor"))
                        .addChild(new ColorLiteral("#abcdef"))));
        /*
        p {
            width: ParWidth;
            height: ParHeight;
            color: #ffffff;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("ParWidth")))
                .addChild(new Declaration("height")
                        .addChild(new VariableReference("ParHeight")))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#ffffff"))));
        /*
        span {
            width: SpanWidth;
            height: 10px;
            color: SpanColor;
            background-color: #cccccc;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("span"))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("SpanWidth")))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("10px")))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("SpanColor")))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#cccccc"))));
        /*
        div {
            width: DivSize;
            DivSize := 11px;
            height: DivSize;
            background-color: DivColor;
            DivColor := #012345;
            color: DivColor;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("div"))
                .addChild(new Declaration("width")
                        .addChild(new VariableReference("DivSize")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivSize"))
                        .addChild(new PixelLiteral("11px")))
                .addChild(new Declaration("height")
                        .addChild(new VariableReference("DivSize")))
                .addChild(new Declaration("background-color")
                        .addChild(new VariableReference("DivColor")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivColor"))
                        .addChild(new ColorLiteral("#012345")))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("DivColor"))));

        return new AST(stylesheet);
    }

    public static AST transformedScopes() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        ParWidth := 28%;
        ParHeight := 13px + 5px;
        SpanWidth := 3 * 10% - 5%;
        SpanColor := #000000;
        DivSize := 5px + 3px * 4;
        DivColor := #abcdef;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PercentageLiteral("28%"))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("ParHeight"))
                        .addChild(new AddOperation()
                                .addChild(new PixelLiteral("13px"))
                                .addChild(new PixelLiteral("5px"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("SpanWidth"))
                        .addChild(new SubtractOperation()
                                .addChild(new MultiplyOperation()
                                        .addChild(new ScalarLiteral(3))
                                        .addChild(new PercentageLiteral("10%")))
                                .addChild(new PercentageLiteral("5%"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("SpanColor"))
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivSize"))
                        .addChild(new AddOperation()
                                .addChild(new PixelLiteral("5px"))
                                .addChild(new MultiplyOperation()
                                        .addChild(new PixelLiteral("3px"))
                                        .addChild(new ScalarLiteral(4)))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivColor"))
                        .addChild(new ColorLiteral("#abcdef"))));
        /*
        p {
            width: 28%;
            height: 18px;
            color: #ffffff;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new Declaration("width")
                        .addChild(new PercentageLiteral("28%")))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("18px")))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#ffffff"))));
        /*
        span {
            width: 25%;
            height: 10px;
            color: #000000;
            background-color: #cccccc;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("span"))
                .addChild(new Declaration("width")
                        .addChild(new PercentageLiteral("25%")))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("10px")))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#000000")))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#cccccc"))));
        /*
        div {
            width: 17px;
            DivSize := 11px;
            height: 11px;
            background-color: #abcdef;
            DivColor := #012345;
            color: #012345;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("div"))
                .addChild(new Declaration("width")
                        .addChild(new PixelLiteral("17px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivSize"))
                        .addChild(new PixelLiteral("11px")))
                .addChild(new Declaration("height")
                        .addChild(new PixelLiteral("11px")))
                .addChild(new Declaration("background-color")
                        .addChild(new ColorLiteral("#abcdef")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("DivColor"))
                        .addChild(new ColorLiteral("#012345")))
                .addChild(new Declaration("color")
                        .addChild(new ColorLiteral("#012345"))));

        return new AST(stylesheet);
    }
}
