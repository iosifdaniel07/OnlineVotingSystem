package com.sistemonlinevoting.Server.thread;

public class ThreadExecutorQuery implements Runnable{

    private MethodDelegate methodDelegate;
    private boolean shouldRun = true;

    public ThreadExecutorQuery(MethodDelegate methodDelegate){
        this.methodDelegate = methodDelegate;
    }

    @Override
    public void run() {
        while(shouldRun){
            methodDelegate.executeQuery();
            shouldRun = false;
        }
    }

    public boolean isRunning(){
        return shouldRun;
    }
}
