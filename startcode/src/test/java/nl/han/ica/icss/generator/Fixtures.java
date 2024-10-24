package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class Fixtures {
    public static AST transformedLevel2() {
        Stylesheet stylesheet = new Stylesheet();
		/*
		p {
			background-color: #ffffff;
			width: 500px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("p"))
				.addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("500px")))
		);
		/*
		a {
			color: #ff0000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("a"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ff0000")))
		);
		/*
		#menu {
			width: 520px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#menu"))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("520px")))
		);
		/*
		.menu {
			color: #000000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new ClassSelector(".menu"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#000000")))
		);

        return new AST(stylesheet);
    }

    public static String generatedLevel2() {
        return  "p {\n" +
                "  background-color: #ffffff;\n" +
                "  width: 500px;\n" +
                "}\n" +
                "a {\n" +
                "  color: #ff0000;\n" +
                "}\n" +
                "#menu {\n" +
                "  width: 520px;\n" +
                "}\n" +
                ".menu {\n" +
                "  color: #000000;\n" +
                "}\n"
        ;
    }

    public static AST transformedLevel3() {
        Stylesheet stylesheet = new Stylesheet();
		/*
		p {
            background-color: #ffffff;
            width: 500px;
            color: #124532;
            background-color: #000000;
            height: 20px;
        }
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("p"))
				.addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("500px")))
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#124532")))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#000000")))
                .addChild((new Declaration("height"))
                        .addChild(new PixelLiteral("20px")))
		);
		/*
		a {
			color: #ff0000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("a"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#ff0000")))
		);
		/*
		#menu {
			width: 520px;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new IdSelector("#menu"))
				.addChild((new Declaration("width"))
						.addChild(new PixelLiteral("520px")))
		);
		/*
		.menu {
			color: #000000;
            background-color: #ff0000;
		}
		*/
		stylesheet.addChild((new Stylerule())
				.addChild(new ClassSelector(".menu"))
				.addChild((new Declaration("color"))
						.addChild(new ColorLiteral("#000000")))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ff0000")))
		);

        return new AST(stylesheet);
    }

    public static String generatedLevel3() {
        return  "p {\n" +
                "  background-color: #ffffff;\n" +
                "  width: 500px;\n" +
				"  color: #124532;\n" +
				"  background-color: #000000;\n" +
				"  height: 20px;\n" +
                "}\n" +
                "a {\n" +
                "  color: #ff0000;\n" +
                "}\n" +
                "#menu {\n" +
                "  width: 520px;\n" +
                "}\n" +
                ".menu {\n" +
                "  color: #000000;\n" +
				"  background-color: #ff0000;\n" +
                "}\n"
        ;
    }

	public static AST transformedScopes() {
		Stylesheet stylesheet = new Stylesheet();

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
			height: 11px;
			background-color: #abcdef;
			color: #012345;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("div"))
				.addChild(new Declaration("width")
						.addChild(new PixelLiteral("17px")))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral("11px")))
				.addChild(new Declaration("background-color")
						.addChild(new ColorLiteral("#abcdef")))
				.addChild(new Declaration("color")
						.addChild(new ColorLiteral("#012345"))));

		return new AST(stylesheet);
	}

	public static String generatedScopes() {
		return  "p {\n" +
				"  width: 28%;\n" +
				"  height: 18px;\n" +
				"  color: #ffffff;\n" +
				"}\n" +
				"span {\n" +
				"  width: 25%;\n" +
				"  height: 10px;\n" +
				"  color: #000000;\n" +
				"  background-color: #cccccc;\n" +
				"}\n" +
				"div {\n" +
				"  width: 17px;\n" +
				"  height: 11px;\n" +
				"  background-color: #abcdef;\n" +
				"  color: #012345;\n" +
				"}\n"
		;
	}

    public static AST transformedExpressions() {
        Stylesheet stylesheet = new Stylesheet();

		/*
		A := TRUE;
		B := #ffffff;
		C := 14%;
		D := B;
		E := 3% + 4%;
		F := 3 + 4;
		G := 3px - 4px;
		H := 3 * 4;
		I := 3 * 4px;
		J := 3% * 4;
		 */
		stylesheet.addChild(new VariableAssignment()
						.addChild(new VariableReference("A"))
						.addChild(new BoolLiteral(true)))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("B"))
						.addChild(new ColorLiteral("#ffffff")))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("C"))
						.addChild(new PercentageLiteral("14%")))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("D"))
						.addChild(new VariableReference("B")))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("E"))
						.addChild(new AddOperation()
								.addChild(new PercentageLiteral("3%"))
								.addChild(new PercentageLiteral("4%"))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("F"))
						.addChild(new AddOperation()
								.addChild(new ScalarLiteral(3))
								.addChild(new ScalarLiteral(4))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("G"))
						.addChild(new SubtractOperation()
								.addChild(new PixelLiteral("3px"))
								.addChild(new PixelLiteral("4px"))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("H"))
						.addChild(new MultiplyOperation()
								.addChild(new ScalarLiteral(3))
								.addChild(new ScalarLiteral(4))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("I"))
						.addChild(new MultiplyOperation()
								.addChild(new ScalarLiteral(3))
								.addChild(new PixelLiteral("4px"))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("J"))
						.addChild(new MultiplyOperation()
								.addChild(new PercentageLiteral("3%"))
								.addChild(new ScalarLiteral(4))));
		/*
		p {
			color: #ffffff;
			background-color: #ffffff;
			width: 11px;
			height: 2%;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("p"))
				.addChild(new Declaration("color")
						.addChild(new ColorLiteral("#ffffff")))
				.addChild(new Declaration("background-color")
						.addChild(new ColorLiteral("#ffffff")))
				.addChild(new Declaration("width")
						.addChild(new PixelLiteral("11px")))
				.addChild(new Declaration("height")
						.addChild(new PercentageLiteral("2%"))));
		/*
		span {
			width: 47%;
			height: -1px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("span"))
				.addChild(new Declaration("width")
						.addChild(new PercentageLiteral("47%")))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral(-1))));
		/*
		div {
			width: 96%;
			height: 12px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("div"))
				.addChild(new Declaration("width")
						.addChild(new PercentageLiteral("96%")))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral("12px"))));
		/*
		.menu {
			width: -3px;
			height: 22%;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new ClassSelector(".menu"))
				.addChild(new Declaration("width")
						.addChild(new PixelLiteral(-3)))
				.addChild(new Declaration("height")
						.addChild(new PercentageLiteral("22%"))));
		/*
		#menu {
			width: 18px;
			height: 5px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new IdSelector("#menu"))
				.addChild(new Declaration("width")
						.addChild(new PixelLiteral("18px")))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral("5px"))));

		return new AST(stylesheet);
    }

	public static String generatedExpressions() {
		return  "p {\n" +
				"  color: #ffffff;\n" +
				"  background-color: #ffffff;\n" +
				"  width: 11px;\n" +
				"  height: 2%;\n" +
				"}\n" +
				"span {\n" +
				"  width: 47%;\n" +
				"  height: -1px;\n" +
				"}\n" +
				"div {\n" +
				"  width: 96%;\n" +
				"  height: 12px;\n" +
				"}\n" +
				".menu {\n" +
				"  width: -3px;\n" +
				"  height: 22%;\n" +
				"}\n" +
				"#menu {\n" +
				"  width: 18px;\n" +
				"  height: 5px;\n" +
				"}\n"
		;
	}
}
