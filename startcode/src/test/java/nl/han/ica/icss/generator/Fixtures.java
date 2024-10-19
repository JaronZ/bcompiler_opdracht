package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
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
}
