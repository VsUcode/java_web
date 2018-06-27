/**
 * Created by Administrator on 2017/08/12.
 */


$(document).ready(function(){
    // var c=document.getElementById("drawbox");
    // var draw=c.getContext("2d");            //获取2d内容的引用，调用绘图API
    //
    // draw.fillStyle="red";                    //颜色
    // draw.beginPath();                        //从新画
    // draw.arc(50,50,50,0,Math.PI*2,true);     //圆心x坐标|圆心y坐标|直径|始|PI为圆周率，Math.PI*2为画圆|true为时针方向：逆时针，0为顺时针，
    // draw.closePath();                        //结束
    // draw.fill();

    // var canvas=document.getElementById("drawbox");
    // var context=canvas.getContext("2d");
    // var x=150;
    // var y=300;
    // var radius=100;
    // var strart=0;
    // var end=2*Math.PI;
    // context.arc(x,y,radius,strart,end);
    // context.fillStyle="blue";
    // context.fill();
    // context.stroke();

    // // 空心圆
    // var canvas2=document.getElementById("drawbox");
    // var context2=canvas2.getContext("2d");
    // var x2=150;
    // var y2=300;
    // var radius2=100;
    // var strart2=0;
    // var end2=2*Math.PI;
    // context.fillStyle="red";
    // context2.arc(x2,y2,radius2,strart2,end2);
    // context.fillStyle="blue";
    // context2.stroke();
    CanvasRenderingContext2D.prototype.sector = function (x, y, radius, sDeg, eDeg) {
        // 初始保存
        this.save();
        // 位移到目标点
        this.translate(x, y);
        this.beginPath();
        // 画出圆弧
        this.arc(0,0,radius,sDeg, eDeg);
        // 再次保存以备旋转
        this.save();
        // 旋转至起始角度
        this.rotate(eDeg);
        // 移动到终点，准备连接终点与圆心
        this.moveTo(radius,0);
        // 连接到圆心
        this.lineTo(0,0);
        // 还原
        this.restore();
        // 旋转至起点角度
        this.rotate(sDeg);
        // 从圆心连接到起点
        this.lineTo(radius,0);
        this.closePath();
        // 还原到最初保存的状态
        this.restore();
        return this;
    }
    window.onload = function(){

        var ctx = document.getElementById('drawbox').getContext('2d');
        //弧度
        var deg = Math.PI/180;

        //扇形横坐标、纵坐标
        var firpoint = 750;
        var sedpoint = 350;

        //扇形半径
        var r = 150;

        //颜色渐变改变值
        var change = 0.1;

        //描绘最外层圆
        ctx.beginPath();

        var g2 = ctx.createRadialGradient(250,250,0,250,250,300);
        g2.addColorStop(0.1,'orange');

        g2.addColorStop(1,'#d3a4ff');

        for(var i = 0; i < 10; i++)
        {
            //绘制扇形
            ctx.fillStyle = g2;
            ctx.sector(firpoint,sedpoint,r,i*36*deg,(i+1)*36*deg);
            ctx.fill();

            //改变扇形颜色渐变值,此处为重点
            change = change+0.05;
            g2.addColorStop(change,'orange');
            g2.addColorStop(1,'purple');
        }
        ctx.closePath();
        ctx.save();

        //内心圆
        r = 100;
        ctx.fillStyle="white";
        ctx.sector(firpoint,sedpoint,r,0*deg,360*deg);
        ctx.fill();
    }
})