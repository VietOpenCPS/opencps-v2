<!-- TODO Template Xây dựng navigation -->
<div id="menu_template">
	<div class="layout wrap">
                <div class="flex xs12 " v-if="!traCuuFilter" jx-bind="serviceInfoFilter"></div>
                <div class="flex xs12 " v-if="!traCuuFilter" jx-bind="applicantNameFilter"></div>
                <div class="flex xs12 mb-2" v-if="!traCuuFilter" jx-bind="dossierNoFilter"></div>
                <v-expansion-panel light class="panel-dossier-navigation">
                        <v-expansion-panel-content :key="1">
                                <div slot="header" @click="filterAllDossierWithOutStatus()">HỒ SƠ CẦN XỬ LÝ</div>
                                <div class="flex xs12 status-dossier-navigation" jx-bind="listgroupHoSoFilter"></div>
                        </v-expansion-panel-content>
                        <v-expansion-panel-content :key="2">
                                <div slot="header" @click="filterTraCuu()">TRA CỨU</div>
                                <div class="flex xs12 tracuu-dossier-navigation" jx-bind="listgroupTraCuuFilter"></div>
                        </v-expansion-panel-content>
                </v-expansion-panel>
	</div>
</div>