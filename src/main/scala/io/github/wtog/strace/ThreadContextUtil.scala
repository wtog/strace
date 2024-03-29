package io.github.wtog.strace

import java.util.UUID

/**
  * @author : tong.wang
  * @since : 2019-07-02 07:08
  * @version : 1.0.0
  */
object ThreadContextUtil {

  val currentThread = new ThreadLocal[ThreadContext]()

  private[this] def sourceId = UUID.randomUUID().toString.replace("-", "")

  def init(guid: Option[String] = None): Unit = {
    val ct = Thread.currentThread()
    currentThread.set(ThreadContext(guid.getOrElse(sourceId), String.valueOf(ct.getId)))
  }

  def copyContext(threadContext: ThreadContext) = this.currentThread.set(threadContext)

  def getContext = Option(currentThread.get())

  def cleanContext() = currentThread.remove()
}

case class ThreadContext(guid: String, tid: String)
