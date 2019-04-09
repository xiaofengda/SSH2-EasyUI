var method = "";
	$(function(){
		$('#grid').datagrid({
		    url:name+'_listByPage',
		    columns: columns,
		    //默认只允许选中一行
		    singleSelect: true,
		    //底部分页工具栏
		    pagination: true,
		    //初始化页面显示条数
		    pageSize: 10,
		    //初始化页面尺寸的选择列表。
		    pageList: [5,10,15,20,25,30],
		    toolbar: [{
		    	text: '新增',
				iconCls: 'icon-add',
				handler: function(){
					//新增方法
					method = "add";
					//打开表单之前清空form表单
					$('#editDlg').form('clear');
					//打开表单
					$('#editDlg').dialog('open');
				}
			}]
		    
		});
		
		//查询
		$("#btnSearch").bind('click', function(){
			//把表单数据转成json对象
			var formData = $('#searchForm').serializeJSON();
			$('#grid').datagrid('load', formData);
		});
		
		$('#editDlg').dialog({    
		    title: '部门编辑',    
		    width: 300,
		    height: 200,
		    closed: true,//窗口是是否为关闭状态, true：表示关闭    
		    modal: true//模式窗口
		});
		
		//添加
		$('#btnSave').bind('click', function(){
			//把表单数据转成json对象
			var formData = $('#editForm').serializeJSON();
			$.ajax({
				type : 'post',
				url : name+'_' + method,
				dataType : 'json',
				data: formData,
				success : function(rtn) {
					$.messager.alert('提示', rtn.message, 'info', function(){
						//成功的话，我们要关闭窗口
						$('#editDlg').dialog('close');
						//刷新表格数据
						$('#grid').datagrid('reload'); 
						
					});
				}
			});
			
			
		});
		
	});
	
	//修改
	function edit(uuid) {
		//弹出窗口
		$('#editDlg').dialog('open');
		//清空表单内容
		$('#editForm').form('clear');
		//加载数据
		$('#editForm').form('load', name+'_get?id=' + uuid);
		//修改方法
		method = "update";

	}
	//删除
	function del(uuid) {
		$.messager.confirm('删除', '您确定要删除吗?', function(yes) {
			if (yes) {
				$.ajax({
					type : 'post',
					url : name+'_delete?id=' + uuid,
					dataType : 'json',
					success : function(rtn) {
						$.messager.alert('提示', rtn.message, 'info', function(){
							//刷新表格数据
							$('#grid').datagrid('reload'); 
						});
					}
				});
			}
		});
	}