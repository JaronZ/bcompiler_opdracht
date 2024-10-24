package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    public static AST uncheckedLevel0() {
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

	public static AST uncheckedLevel1() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("LinkColor"))
                .addChild(new ColorLiteral("#ff0000"))
        );
		stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("500px"))
        );
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
	        background-color: #ffffff;
	        width: ParWidth;
            }
	    */
        stylesheet.addChild((new Stylerule())
            .addChild(new TagSelector("p"))
            .addChild((new Declaration("background-color"))
                    .addChild(new ColorLiteral("#ffffff")))
            .addChild((new Declaration("width"))
                    .addChild(new VariableReference("ParWidth")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor")))
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

	public static AST uncheckedLevel2() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("LinkColor"))
				.addChild(new ColorLiteral("#ff0000"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("ParWidth"))
				.addChild(new PixelLiteral("500px"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
	        background-color: #ffffff;
	        width: ParWidth;
            }
	    */
        stylesheet.addChild((new Stylerule())
            .addChild(new TagSelector("p"))
            .addChild((new Declaration("background-color"))
                    .addChild(new ColorLiteral("#ffffff")))
            .addChild((new Declaration("width"))
                    .addChild(new VariableReference("ParWidth")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor")))
		);
        /*
            #menu {
        	width: ParWidth + 2 * 10px;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new IdSelector("#menu"))
			.addChild((new Declaration("width"))
				.addChild((new AddOperation())
                        .addChild(new VariableReference("ParWidth"))
                        .addChild((new MultiplyOperation())
                                .addChild(new ScalarLiteral("2") )
                                .addChild(new PixelLiteral("10px"))

        ))));
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

	public static AST uncheckedLevel3() {
		Stylesheet stylesheet = new Stylesheet();
		/*
			LinkColor := #ff0000;
			ParWidth := 500px;
			AdjustColor := TRUE;
			UseLinkColor := FALSE;
		 */
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("LinkColor"))
				.addChild(new ColorLiteral("#ff0000"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("ParWidth"))
				.addChild(new PixelLiteral("500px"))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("AdjustColor"))
				.addChild(new BoolLiteral(true))
		);
		stylesheet.addChild((new VariableAssignment())
				.addChild(new VariableReference("UseLinkColor"))
				.addChild(new BoolLiteral(false))
		);
   	    /*
	        p {
				background-color: #ffffff;
				width: ParWidth;
				if[AdjustColor] {
	    			color: #124532;
	    			if[UseLinkColor]{
	        			bg-color: LinkColor;
	    			}
				}
			}
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
	    		height: 20px;
			}
}
	    */
        stylesheet.addChild((new Stylerule())
				.addChild(new TagSelector("p"))
					.addChild((new Declaration("background-color"))
							.addChild(new ColorLiteral("#ffffff")))
					.addChild((new Declaration("width"))
							.addChild(new VariableReference("ParWidth")))
					.addChild((new IfClause())
						.addChild(new VariableReference("AdjustColor"))
						.addChild((new Declaration("color")
								.addChild(new ColorLiteral("#124532"))))
							.addChild((new IfClause())
									.addChild(new VariableReference("UseLinkColor"))
									.addChild(new Declaration("background-color").addChild(new VariableReference("LinkColor")))
									.addChild((new ElseClause())
											.addChild(new Declaration("background-color").addChild(new ColorLiteral("#000000")))

									)
					))
					.addChild((new Declaration("height"))
							.addChild(new PixelLiteral("20px")))
        );
        /*
        a {
	        color: LinkColor;
        }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new TagSelector("a"))
			.addChild((new Declaration("color"))
				.addChild(new VariableReference("LinkColor"))
            )
		);
        /*
            #menu {
        	width: ParWidth + 20px;
            }
        */
        stylesheet.addChild((new Stylerule())
			.addChild(new IdSelector("#menu"))
			.addChild((new Declaration("width"))
				.addChild((new AddOperation())
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("20px"))
                )
            )
        );
        /*


         .menu {
				color: #000000;
    			background-color: LinkColor;

			}

        */
        stylesheet.addChild((new Stylerule())
			.addChild(new ClassSelector(".menu"))

			.addChild((new Declaration("color"))
				.addChild(new ColorLiteral("#000000"))
            )
				.addChild((new Declaration("background-color"))
						.addChild(new VariableReference("LinkColor"))
				)

		);

		return new AST(stylesheet);
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

	public static AST uncheckedScopes() {
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
						.addChild(new PercentageLiteral("28%")))
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
						.addChild(new ColorLiteral("#abcdef")));
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
                        .addChild(new PercentageLiteral("28%")))
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
                        .addChild(new ColorLiteral("#abcdef")));
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

	public static AST uncheckedExpressions() {
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
			color: B;
			background-color: D;
			width: 5px + 6px;
			height: 3% - 1%;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("p"))
				.addChild(new Declaration("color")
						.addChild(new VariableReference("B")))
				.addChild(new Declaration("background-color")
						.addChild(new VariableReference("D")))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new PixelLiteral("5px"))
								.addChild(new PixelLiteral("6px"))))
				.addChild(new Declaration("height")
						.addChild(new SubtractOperation()
								.addChild(new PercentageLiteral("3%"))
								.addChild(new PercentageLiteral("1%")))));
		/*
		span {
			width: 5% * F + J;
			height: G;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("span"))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new MultiplyOperation()
										.addChild(new PercentageLiteral("5%"))
										.addChild(new VariableReference("F")))
								.addChild(new VariableReference("J"))))
				.addChild(new Declaration("height")
						.addChild(new VariableReference("G"))));
		/*
		div {
			width: H * 4 * 2%;
			height: 3 * 4px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("div"))
				.addChild(new Declaration("width")
						.addChild(new MultiplyOperation()
								.addChild(new MultiplyOperation()
										.addChild(new VariableReference("H"))
										.addChild(new ScalarLiteral(4)))
								.addChild(new PercentageLiteral("2%"))))
				.addChild(new Declaration("height")
						.addChild(new MultiplyOperation()
								.addChild(new ScalarLiteral(3))
								.addChild(new PixelLiteral("4px")))));
		/*
		.menu {
			width: G - 2px;
			if [A] {
				height: 2% + C + 6%;
			} else {
				height: C - E;
			}
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new ClassSelector(".menu"))
				.addChild(new Declaration("width")
						.addChild(new SubtractOperation()
								.addChild(new VariableReference("G"))
								.addChild(new PixelLiteral("2px"))))
				.addChild(new IfClause()
						.addChild(new VariableReference("A"))
						.addChild(new Declaration("height")
								.addChild(new AddOperation()
										.addChild(new AddOperation()
												.addChild(new PercentageLiteral("2%"))
												.addChild(new VariableReference("C")))
										.addChild(new PercentageLiteral("6%"))))
						.addChild(new ElseClause()
								.addChild(new Declaration("height")
										.addChild(new SubtractOperation()
												.addChild(new VariableReference("C"))
												.addChild(new VariableReference("E")))))));
		/*
		#menu {
			width: I + 2 * 3px;
			height: 5px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new IdSelector("#menu"))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new VariableReference("I"))
								.addChild(new MultiplyOperation()
										.addChild(new ScalarLiteral(2))
										.addChild(new PixelLiteral("3px")))))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral("5px"))));

		return new AST(stylesheet);
	}

    public static AST checkedExpressions() {
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
			color: B;
			background-color: D;
			width: 5px + 6px;
			height: 3% - 1%;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("p"))
				.addChild(new Declaration("color")
						.addChild(new VariableReference("B")))
				.addChild(new Declaration("background-color")
						.addChild(new VariableReference("D")))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new PixelLiteral("5px"))
								.addChild(new PixelLiteral("6px"))))
				.addChild(new Declaration("height")
						.addChild(new SubtractOperation()
								.addChild(new PercentageLiteral("3%"))
								.addChild(new PercentageLiteral("1%")))));
		/*
		span {
			width: 5% * F + J;
			height: G;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("span"))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new MultiplyOperation()
										.addChild(new PercentageLiteral("5%"))
										.addChild(new VariableReference("F")))
								.addChild(new VariableReference("J"))))
				.addChild(new Declaration("height")
						.addChild(new VariableReference("G"))));
		/*
		div {
			width: H * 4 * 2%;
			height: 3 * 4px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new TagSelector("div"))
				.addChild(new Declaration("width")
						.addChild(new MultiplyOperation()
								.addChild(new MultiplyOperation()
										.addChild(new VariableReference("H"))
										.addChild(new ScalarLiteral(4)))
								.addChild(new PercentageLiteral("2%"))))
				.addChild(new Declaration("height")
						.addChild(new MultiplyOperation()
								.addChild(new ScalarLiteral(3))
								.addChild(new PixelLiteral("4px")))));
		/*
		.menu {
			width: G - 2px;
			if [A] {
				height: 2% + C + 6%;
			} else {
				height: C - E;
			}
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new ClassSelector(".menu"))
				.addChild(new Declaration("width")
						.addChild(new SubtractOperation()
								.addChild(new VariableReference("G"))
								.addChild(new PixelLiteral("2px"))))
				.addChild(new IfClause()
						.addChild(new VariableReference("A"))
						.addChild(new Declaration("height")
								.addChild(new AddOperation()
										.addChild(new AddOperation()
												.addChild(new PercentageLiteral("2%"))
												.addChild(new VariableReference("C")))
										.addChild(new PercentageLiteral("6%"))))
						.addChild(new ElseClause()
								.addChild(new Declaration("height")
										.addChild(new SubtractOperation()
												.addChild(new VariableReference("C"))
												.addChild(new VariableReference("E")))))));
		/*
		#menu {
			width: I + 2 * 3px;
			height: 5px;
		}
		 */
		stylesheet.addChild(new Stylerule()
				.addChild(new IdSelector("#menu"))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new VariableReference("I"))
								.addChild(new MultiplyOperation()
										.addChild(new ScalarLiteral(2))
										.addChild(new PixelLiteral("3px")))))
				.addChild(new Declaration("height")
						.addChild(new PixelLiteral("5px"))));

		return new AST(stylesheet);
    }

    public static AST uncheckedErrors() {
        Stylesheet stylesheet = new Stylesheet();

        /*
        Pixel := 10px;
        Color := #ffffff;
        MultWithTwoPixels := 2px * 10px;
        AddWithDifferentTypes := 10 + 10%;
        OperationWithColor := 10 * #ffffff;
        AddPixelWithColor := Pixel + Color;
         */
        stylesheet.addChild(new VariableAssignment()
                        .addChild(new VariableReference("Pixel"))
                        .addChild(new PixelLiteral("10px")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("Color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("MultWithTwoPixels"))
                        .addChild(new MultiplyOperation()
                                .addChild(new PixelLiteral("2px"))
                                .addChild(new PixelLiteral("10px"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("AddWithDifferentTypes"))
                        .addChild(new AddOperation()
                                .addChild(new ScalarLiteral(10))
                                .addChild(new PercentageLiteral("10%"))))
                .addChild(new VariableAssignment()
                        .addChild(new VariableReference("OperationWithColor"))
                        .addChild(new MultiplyOperation()
                                .addChild(new ScalarLiteral(10))
                                .addChild(new ColorLiteral("#ffffff"))))
				.addChild(new VariableAssignment()
						.addChild(new VariableReference("AddPixelWithColor"))
						.addChild(new AddOperation()
								.addChild(new VariableReference("Pixel"))
								.addChild(new VariableReference("Color"))));
        /*
        p {
            if [10] {
                color: 10px;
            }
            if [3 * 5%] {
                background-color: #ffffff;
            }
            width: #ffffff;
            height: ThisVariableDoesNotExist;
            color: ThisVariableDoesNotExist2;
            color: Pixel;
            height: Color;
            this-property: #ffffff;
            width: 10px + 10% * 10;
            height: 10px * 10 * 10px;
        }
         */
        stylesheet.addChild(new Stylerule()
                .addChild(new TagSelector("p"))
                .addChild(new IfClause()
                        .addChild(new ScalarLiteral(10))
                        .addChild(new Declaration("color")
                                .addChild(new PixelLiteral("10px"))))
                .addChild(new IfClause()
                        .addChild(new MultiplyOperation()
                                .addChild(new ScalarLiteral(3))
                                .addChild(new PercentageLiteral("5%")))
                        .addChild(new Declaration("background-color")
                                .addChild(new ColorLiteral("#ffffff"))))
                .addChild(new Declaration("width")
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild(new Declaration("height")
                        .addChild(new VariableReference("ThisVariableDoesNotExist")))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("ThisVariableDoesNotExist2")))
                .addChild(new Declaration("color")
                        .addChild(new VariableReference("Pixel")))
                .addChild(new Declaration("height")
                        .addChild(new VariableReference("Color")))
                .addChild(new Declaration("this-property")
                        .addChild(new ColorLiteral("#ffffff")))
				.addChild(new Declaration("width")
						.addChild(new AddOperation()
								.addChild(new PixelLiteral("10px"))
								.addChild(new MultiplyOperation()
										.addChild(new PercentageLiteral("10%"))
										.addChild(new ScalarLiteral(10)))))
				.addChild(new Declaration("height")
						.addChild(new MultiplyOperation()
								.addChild(new PixelLiteral("10px"))
								.addChild(new MultiplyOperation()
										.addChild(new ScalarLiteral(10))
										.addChild(new PixelLiteral("10px"))
								))));

        return new AST(stylesheet);
    }

    public static List<SemanticError> errorsFromUncheckedErrors() {
        List<SemanticError> errors = new ArrayList<>();

		errors.add(new SemanticError("Cannot multiply non-scalars"));
		errors.add(new SemanticError("Cannot add or subtract different types"));
		errors.add(new SemanticError("Cannot use color literals in operations"));
		errors.add(new SemanticError("Cannot add or subtract different types"));
		errors.add(new SemanticError("Invalid type for conditional. Expected boolean, got ScalarLiteral"));
		errors.add(new SemanticError("Invalid type for color property. Expected color, got PixelLiteral"));
		errors.add(new SemanticError("Invalid type for conditional. Expected boolean, got operation"));
		errors.add(new SemanticError("Invalid type for size property. Expected pixel or percentage, got ColorLiteral"));
		errors.add(new SemanticError("Variable 'ThisVariableDoesNotExist' used before assignment"));
		errors.add(new SemanticError("Variable 'ThisVariableDoesNotExist2' used before assignment"));
		errors.add(new SemanticError("Invalid type for variable 'Pixel'. Expected COLOR, got PIXEL"));
		errors.add(new SemanticError("Invalid type for variable 'Color'. Expected one of [PIXEL, PERCENTAGE], got COLOR"));
		errors.add(new SemanticError("Invalid property 'this-property'"));
		errors.add(new SemanticError("Cannot add or subtract different types"));
		errors.add(new SemanticError("Cannot multiply non-scalars"));

        return errors;
    }
}
