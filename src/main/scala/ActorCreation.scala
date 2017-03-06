import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by hyan on 12/29/16.
  */
object MusicController {
  sealed trait ControllerMsg
  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg

  def props = Props[MusicController]
}

class MusicController extends Actor {
  override def receive = {
    case MusicController.Play =>
      println("Music started ......")
    case MusicController.Stop =>
      println("Music stopped ......")
  }
}

object MusicPlayer {
  sealed trait PlayMsg
  case object StopMusic extends PlayMsg
  case object StartMusic extends PlayMsg

  def props = Props[MusicPlayer]
}

class MusicPlayer extends Actor {
  override def receive = {
    case MusicPlayer.StopMusic =>
      println("I don't want to stop music")
    case MusicPlayer.StartMusic =>
      val controller = context.actorOf(Props[MusicController])
      controller ! MusicController.Play
    case _ =>
      println("Unknown Message")
  }
}

object Creation extends App {
  val system = ActorSystem("creation")

  val player = system.actorOf(MusicPlayer.props)

  player ! MusicPlayer.StartMusic

  player ! MusicPlayer.StopMusic

  system.terminate()

}
