package egc.decide.io.cabinatelegram.bot.actions;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class JokeAction implements DecideBotAction{

	private static String[] JOKES = {"¿Qué es una gamba tirando piedras? Una Gamberra.","-Paco ¿ha aprendido tu hija a andar ya?\n+Sí, lleva ya tres meses andando\n-Pues sí que estará lejos la niña...",
			"¿Qué le dice un pino a otro pino? Arráscame el pepino", "-Papá quiero un iPhone X\n+¿Cuál es la palabra mágica, hijo?\n-Paula\n+¿Quién es Paula?\n-Tu amante, papá\n+¿Lo quieres con funda también?",
			"Le dice Jaimito a su madre: - Mamá ¿Para qué te pintas?\n+Para estar guapa, Jaimito\n-¿Y tarda mucho en hacer efecto?", "Hay 10 tipos de personas, los que saben binario y los que no.",
			"¿Por que se murieron 326 gallegos en el mar?\n-Por que se paro el barco y todos se bajaron al empujar.","Mi ordenador me gana al ajedrez, pero yo le gano boxeando.", "Es mejor dar que recibir\n\nFirmado, Un boxeador.",
			"¿Cuántos botones tendrá el nuevo teclado del Windows 2024?\n- Solo 3 CTRL, ALT, SUPR","En un coche hay tres ingenieros: uno es mecánico, otro electrónico y el último es informático.\nDe repente el coche se para, y el mecánico dice:"+
			"\n-Me bajo y le echo un vistazo a ver que pasa.\nEl ingeniero mira el motor, aprieta las tuercas, revisa la gasolina...\n-Pues yo no he encontrado nada raro, dice.\n-Espera que me bajo yo a ver si le falla algo eléctrico, dice el segundo.\n" + 
			"El electrónico mira la conexión entre cables, la batería...\n-Pues yo me he quedado igual, dice.\nY a esto salta el informático:\n-Oye, ¿qué tal si probamos a bajarnos y a subirnos otra vez?","¿Qué le dice una IP a otra?\n¿Qué tramas?",
			".¡Soldado Miralles!\n¡¿Si, mi capitán?¡\nNo lo vi ayer en la prueba de camuflaje.\n¡Gracias mi capitán!","Pregunta de examen: ¿cómo desaparecieron los mayas?\nRespuesta: los sustituyeron los leggins...",
			"En una entrevista de trabajo:\n- ¿Nivel de inglés? \n- Alto \n- Bien. Traduzca \"fiesta\". \n- Party\n- Perfecto. Úselo en una frase. \n- Ayer me party la cara con la bicicleta. \n- Contratado.",
			"- Doctor, doctor,...me tiro peos sin olor.\n- A ver, tírese uno.\n- Pffff...a usted tenemos que operarlo.\n- ¿De la barriga?\n- No, ¡de la nariz!",
			"Cariño, creo que estás obsesionado con el fútbol y me haces falta.\n- ¡¿Qué falta?! ¡¿Qué falta?! ¡¡Si no te he tocado!!","- Soy un tipo saludable\n- Ah. ¿Comes sano y todo eso?\n- No, la gente me saluda...",
			"- Papá, ¿qué se siente tener un hijo tan guapo?.\n+ No sé hijo, pregúntale a tu abuelo...","-Mamá, no te asustes, pero te estoy llamando desde el hospital...\n+Hijo, hace 5 años que eres doctor y todavía sigues con la misma broma...?"};
	
	@Override
	public BotApiMethod<?> act(Update update) throws DecideBotException {
		return new SendMessage().setChatId(update.getMessage().getChatId()).setText(JOKES[(int) (Math.random() * JOKES.length)]); 
	}
}
