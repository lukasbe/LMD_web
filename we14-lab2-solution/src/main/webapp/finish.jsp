<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% org.apache.log4j.Logger.getLogger("start.jsp").info("finish.jsp wurde aufgerufen!"); %>
<jsp:useBean id="gameBean" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.GameBean" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz - Spielende</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
    </head>
    <body id="winnerpage" onLoad="timeStamp();">
        <a class="accessibility" href="#roundwinner">Zur Spielwertung springen</a>
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
                <h2 id="roundwinnerheading" class="accessibility">Endstand</h2>
                <p class="roundwinnermessage">Spieler 2 gewinnt!</p>
            </section>
        
            <!-- round info -->    
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info" class="playerinfo">
                    <span id="player1name" class="playername">Spieler 1</span>
                    <p id="player1roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player1wonrounds" class="playerwonrounds">2</span></p>
                </div>
                <div id="player2info" class="playerinfo">
                    <span id="player2name" class="playername">Spieler 2</span>
                    <p id="player2roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player2wonrounds" class="playerwonrounds">1</span></p>
                </div>
                <form id="finishform" action="BigQuizServlet" method="GET">
		        	<input type="hidden" name="action" value="start"/>
					<a id="next" href="javascript:{}" onclick="document.getElementById('finishform').submit();">Neues Spiel</a>
				</form>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
    
          
         <script type="text/javascript">
            //<![CDATA[
         
                       function timeStamp() {
                       if(supportsLocalStorage()==true){
                    	   	var d = new Date();
                    	    var date = d.getDate();
                    	    var month = d.getMonth() + 1;
                    	    var year = d.getFullYear();
                    	    var formatedDate = date + "." + month + "." + year;
                    	    localStorage.setItem("date", formatedDate);
                       }
                       }
         </script>
    
    </body>
</html>
