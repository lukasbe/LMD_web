<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% org.apache.log4j.Logger.getLogger("start.jsp").info("roundcomplete.jsp wurde aufgerufen!"); %>
<jsp:useBean id="gameBean" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.GameBean" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz - Zwischenstand</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
    </head>
    <body id="winnerpage">
        <a class="accessibility" href="#roundwinner">Zur Rundenwertung springen</a>
        <header role="banner" aria-labelledby="mainheading"><h1 id="mainheading"><span class="accessibility">Business Informatics Group</span> Quiz</h1></header>
        <nav role="navigation" aria-labelledby="navheading">
            <h2 id="navheading" class="accessibility">Navigation</h2>
            <ul>
                <li><a id="logoutlink" title="Klicke hier um dich abzumelden" href="#" accesskey="l">Abmelden</a></li>
            </ul>
        </nav>
        
        <section role="main">
            <!-- winner message -->
            <section id="roundwinner" aria-labelledby="roundwinnerheading">
                <h2 id="roundwinnerheading" class="accessibility">Rundenzwischenstand</h2>
                <p class="roundwinnermessage"><%= gameBean.getCurrentRoundWinner() %> gewinnt Runde <%= gameBean.getCurrentRound() %>!</p>
            </section>
        
            <!-- round info -->    
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info" class="playerinfo">
                    <span id="player1name" class="playername"><%=gameBean.getPlayer1()%></span>
                    <ul class="playerroundsummary">
                        <%for(int i = 1; i <= gameBean.getRoundsQuantity(); i++){%>
                        <li><span class="accessibility">Frage <%=i%>:</span><%="<span id=\""%><%="player1answer" + i%>" 	<%if(gameBean.getPlayer1RoundSummary().get(i-1)){%><%="class=\"correct\">Richtig"%><%}%>
                        																					<%if(!gameBean.getPlayer1RoundSummary().get(i-1)){%><%="class=\"incorrect\">Falsch"%><%}%>
                        <%="</span>"%></li>
                        <%}%>
                        </ul>
                    <p id="player1roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player1wonrounds" class="playerwonrounds"><%=gameBean.getPlayer1WonRounds()%></span></p>
                </div>
                <div id="player2info" class="playerinfo">
                    <span id="player2name" class="playername"><%=gameBean.getPlayer2()%></span>
                    <ul class="playerroundsummary">
                       <%for(int i = 1; i <= gameBean.getQuestionsQuantity(); i++){%>
                        <li><span class="accessibility">Frage <%=i%>:</span><%="<span id=\""%><%="player2answer" + i%>" 	<%if(gameBean.getPlayer2RoundSummary().get(i-1)){%><%="class=\"correct\">Richtig"%><%}%>
                        																					<%if(!gameBean.getPlayer2RoundSummary().get(i-1)){%><%="class=\"incorrect\">Falsch"%><%}%>
                        <%="</span>"%></li>
                        <%}%>
                    </ul>
                    <p id="player2roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player2wonrounds" class="playerwonrounds"><%=gameBean.getPlayer2WonRounds() %></span></p>
                </div>
            	<form id="nextform" action="BigQuizServlet" method="GET">
		        	<input type="hidden" name="action" value="roundcompleteweiter"/>
					<a id="next" href="javascript:{}" onclick="document.getElementById('nextform').submit();">Weiter</a>
				</form>
                <!-- <a id="next" href="question.html">Weiter</a> -->
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
    </body>
</html>
