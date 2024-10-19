package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.literals.ColorLiteral;
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
}
