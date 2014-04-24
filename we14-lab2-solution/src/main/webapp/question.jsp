<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="at.ac.tuwien.big.we14.lab2.api.*"%>
<% org.apache.log4j.Logger.getLogger("start.jsp").info("question.jsp wurde aufgerufen!"); %>
<jsp:useBean id="gameBean" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.GameBean" />

<% Question question = (Question)session.getAttribute("question");%>
<% if(question == null) return; %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
    </head>
    <body id="questionpage" onLoad="dispLastGame();">
        <a class="accessibility" href="#question">Zur Frage springen</a>
        <header role="banner" aria-labelledby="mainheading"><h1 id="mainheading"><span class="accessibility">Business Informatics Group</span> Quiz</h1></header>
        <nav role="navigation" aria-labelledby="navheading">
            <h2 id="navheading" class="accessibility">Navigation</h2>
            <ul>
                <li><a id="logoutlink" title="Klicke hier um dich abzumelden" href="#" accesskey="l">Abmelden</a></li>
            </ul>
        </nav>
        
        <!-- round info -->
        <section role="main">
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info">
                    <span id="player1name">Mark</span>
                    <ul class="playerroundsummary">
                        <li><span class="accessibility">Frage 1:</span><span id="player1answer1" class="correct">Richtig</span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player1answer2" class="incorrect">Falsch</span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player1answer3" class="unknown">Unbekannt</span></li>
                    </ul>
                </div>
                <div id="player2info">
                    <span id="player2name">Günther</span>
                    <ul class="playerroundsummary">
                        <li><span class="accessibility">Frage 1:</span><span id="player2answer1" class="correct">Richtig</span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player2answer2" class="correct">Richtig</span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player2answer3" class="unknown">Unbekannt</span></li>
                    </ul>
                </div>
                <div id="currentcategory"><span class="accessibility">Kategorie:</span><%= question.getCategory().getName()%></div>
            </section>
            
            <!-- Question -->
            <section id="question" aria-labelledby="questionheading">
                <form id="questionform" action="BigQuizServlet" method="post">
                    <h2 id="questionheading" class="accessibility">Frage</h2>
                    <p id="questiontext"><%= question.getText()%></p>
                    <ul id="answers">
                    	<% int counter = 1;%>
                        <%for(Choice c : question.getAllChoices()){ %>
                        <li><input id="<%="option" + counter%>" type="checkbox"/><label for="<%="option" + counter++%>"><%= c.getText() %></label></li>
                        <%} %>
                    </ul>
                    <input id="timeleftvalue" type="hidden" value="<%= question.getMaxTime() %>"/>
                    <input id="next" type="submit" value="weiter" accesskey="s"/>
                    <input id="action" type="hidden" name="action" value="questioncomplete"/>
                </form>
            </section>
            
            <section id="timer" aria-labelledby="timerheading">
                <h2 id="timerheading" class="accessibility">Timer</h2>
                <p><span id="timeleftlabel">Verbleibende Zeit:</span> <time id="timeleft"><%= question.getMaxTime() %></time></p>
                <meter id="timermeter" min="0" low="20" value="100" max="100"/>
            </section>
            <section id="lastgame">
                <p id="lg">Letztes Spiel: Nie</p>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
        
        <script type="text/javascript">
            //<![CDATA[
            
            //display lastGame
            function dispLastGame(){
            if(supportsLocalStorage()){
            	if(localStorage.getItem("date") === null)
            		document.getElementById('lg').innerHTML = "Letztes Spiel: Nie"
            
            	else{
            		document.getElementById('lg').innerHTML = "Letztes Spiel: " + localStorage.getItem("date");
            	}
            }
            else{
            	document.getElementById('lg').innerHTML = "Letztes Spiel: Nie";
            }
            }          
            // initialize time
            $(document).ready(function() {
                var maxtime = <%=question.getMaxTime()%>;
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                hiddenInput.val(maxtime);
                meter.val(maxtime);
                meter.attr('max', maxtime);
                meter.attr('low', maxtime/100*20);
                timeleft.text(secToMMSS(maxtime));
            });
            
            // update time
            function timeStep() {
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                var value = $("#timeleftvalue").val();
                if(value > 0) {
                    value = value - 1;   
                }
                
                hiddenInput.val(value);
                meter.val(value);
                timeleft.text(secToMMSS(value));
                
                if(value <= 0) {
                    $('#questionform').submit();
                }
            }
            
            window.setInterval(timeStep, 1000);
            
            //]]>
        </script>
    </body>
</html>
