package channel

import scala.collection.mutable.Map

class Channel[M] {
    var subscribers = Map.empty[Int, M => Unit]
    var nextID = 0

    def subscribe(handler: M => Unit) : () => Unit = {
        val id = nextID
        nextID += 1

        subscribers += id -> handler
        (() => subscribers -= id)
    }

    def publish(message: M): Unit =
        subscribers.foreach(s => s._2(message))

    def >>(handler: M => Unit) = subscribe(handler)
    def <<(message: M) = publish(message)
}

class ChildChannel[M <: P, P](val parent: Channel[P])
    extends Channel[M]
{
    override def publish(message: M) = {
        parent.publish(message.asInstanceOf[P])
        super.publish(message)
    }
}
