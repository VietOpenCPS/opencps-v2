<div class="row">
    <div class="col-sm-12">
        
    </div>
</div>

<div id="serviceinfo_detail_tabstrip">
    <ul>
        <li class="k-state-active">
            Thông tin chung
        </li>
        <li>
            Trình tự thực hiện
        </li>
        <li>
            Thành phần hồ sơ
        </li>
        <li>
            Yêu cầu điều kiện
        </li>
    </ul>
    <div>
        <span class="rainy">&nbsp;</span>
        <div class="weather">
            <h2>17<span>&ordm;C</span></h2>
            <p>Rainy weather in Paris.</p>
        </div>
    </div>
    <div>
        <span class="sunny">&nbsp;</span>
        <div class="weather">
            <h2>29<span>&ordm;C</span></h2>
            <p>Sunny weather in New York.</p>
        </div>
    </div>
    <div>
        <span class="sunny">&nbsp;</span>
        <div class="weather">
            <h2>21<span>&ordm;C</span></h2>
            <p>Sunny weather in London.</p>
        </div>
    </div>
    <div>
        <span class="cloudy">&nbsp;</span>
        <div class="weather">
            <h2>16<span>&ordm;C</span></h2>
            <p>Cloudy weather in Moscow.</p>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $("#serviceinfo_detail_tabstrip").kendoTabStrip({
            animation:  {
                open: {
                    effects: "fadeIn"
                }
            }
        });
    });
	
	
</script>