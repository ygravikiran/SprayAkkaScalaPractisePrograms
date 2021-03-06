package sample

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.Actor
import spray.routing.HttpService
import com.gettyimages.spray.swagger.SwaggerHttpService
 
object Boot extends App {
 
  // create our actor system with the name smartjava
  implicit val system = ActorSystem("smartjava")
  val service = system.actorOf(Props[SJServiceActor], "sj-rest-service")
 
  // IO requires an implicit ActorSystem, and ? requires an implicit timeout
  // Bind HTTP to the specified service.
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}

class Boot extends Actor with Service{
  
  val x = context
  val swaggerService = new SwaggerHttpServiceWithPathPrefixSupport ()
  
  
}


trait Service extends Actor with HttpService {
  
}

trait SwaggerHttpServiceWithPathPrefixSupport extends Actor with SwaggerHttpService {
  
}